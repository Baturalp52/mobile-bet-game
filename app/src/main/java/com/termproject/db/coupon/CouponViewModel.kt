package com.termproject.db.coupon

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.termproject.utils.CouponStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class CouponViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CouponRepository

    var coupon = CouponWithPlayedGames(
        coupon = Coupon(
            status = CouponStatus.PENDING,
            playedAt = LocalDateTime.now()
        ), playedGames = ArrayList()
    )

    init {
        val couponDAO = CouponRoomDatabase.getDatabase(application).couponDao()
        repository = CouponRepository(couponDAO)
    }

    suspend fun getCouponById(couponId: Long): CouponWithPlayedGames {
        return withContext(Dispatchers.IO) { repository.getCouponById(couponId) }
    }

    fun playCoupon() {
        // You can now use the database instance to perform database operations
        GlobalScope.launch {
            coupon.coupon.playedAt = LocalDateTime.now()
            val couponId = repository.newCoupon(coupon = coupon.coupon)
            coupon.playedGames.forEach {
                val playedBetId = repository.newPlayedBet(playedBet = it.playedBet)

                val homeTeam = repository.getTeamByTeamId(it.homeTeam.teamId)

                if (homeTeam == null) {

                    repository.newTeam(it.homeTeam)
                }

                val awayTeam = repository.getTeamByTeamId(it.awayTeam.teamId)
                if (awayTeam == null) {
                    repository.newTeam(it.awayTeam)
                }
                it.playedGame.homeTeamId = it.homeTeam.teamId
                it.playedGame.awayTeamId = it.awayTeam.teamId
                it.playedGame.couponId = couponId
                it.playedGame.playedBetId = playedBetId
                repository.newPlayedGame(it.playedGame)

                coupon = CouponWithPlayedGames(
                    coupon = Coupon(
                        status = CouponStatus.PENDING,
                        playedAt = LocalDateTime.now()
                    ), playedGames = ArrayList()
                )

            }
        }
    }

    fun addGame(game: PlayedGameWithDetails) {
        coupon.playedGames.add(game)
    }


}