package com.berkberaozer.hw2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.termproject.Constants
import com.termproject.classes.ApiKey
import com.termproject.classes.Coupon

@Dao
interface CouponDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoupon(coupon: Coupon)

    @Query("SELECT * FROM ${Constants.COUPONTABLENAME} ORDER BY id ASC")
    fun getAllCoupons():LiveData<List<Coupon>>
}