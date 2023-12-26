package com.termproject.db.coupon

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface CouponDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun newCoupon(coupon: Coupon): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun newPlayedGame(playedGame: PlayedGame): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun newPlayedBet(playedBet: PlayedBet): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun newTeam(team: Team): Long

    @Query("SELECT * FROM teams WHERE teamId =:id")
    fun getTeamByTeamId(id: Long): Team

    @Transaction
    @Query("SELECT * FROM coupons")
    fun getAllCoupons(): List<CouponWithPlayedGames>

    @Transaction
    @Query("SELECT * FROM coupons WHERE id = :couponId")
    fun getCouponById(couponId: Long): CouponWithPlayedGames


}