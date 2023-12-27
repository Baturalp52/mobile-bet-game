package com.termproject

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.termproject.db.coupon.CouponRepository
import com.termproject.db.coupon.CouponRoomDatabase
import com.termproject.utils.BetStatus
import com.termproject.utils.CouponStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Random
import java.util.concurrent.TimeUnit

class OddCheckerWorker(val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    private var repository: CouponRepository

    init {
        val couponDAO = CouponRoomDatabase.getDatabase(context).couponDao()
        repository = CouponRepository(couponDAO)
    }

    override fun doWork(): Result {  // Perform background task here

        CoroutineScope(Dispatchers.IO).launch {
            val pendingCoupons = repository.getPendingCoupons()

            pendingCoupons.forEach { couponWithPlayedGames ->
                val playedGames = couponWithPlayedGames.playedGames
                val coupon = couponWithPlayedGames.coupon
                playedGames.forEach { playedGameWithDetails ->
                    val playedBet = playedGameWithDetails.playedBet

                    if (playedBet.status == BetStatus.PENDING) {
                        val random = Random()
                        if (random.nextBoolean()) {
                            playedBet.status = BetStatus.WIN
                        } else {
                            playedBet.status = BetStatus.LOSE
                        }

                        repository.updateBet(playedBet)
                    }
                }


                val isAllWin =
                    playedGames.map { it.playedBet.status == BetStatus.WIN }
                        .reduce { acc, status -> acc && status }

                if (isAllWin) {
                    coupon.status = CouponStatus.WIN
                } else {
                    coupon.status = CouponStatus.LOSE
                }

                repository.updateCoupon(coupon)
            }


        }




        return Result.success()
    }
}