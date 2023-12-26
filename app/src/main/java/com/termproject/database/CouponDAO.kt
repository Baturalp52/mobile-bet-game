package com.berkberaozer.hw2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.termproject.Constants
import com.termproject.classes.ApiKey
import com.termproject.classes.Coupon
import com.termproject.classes.CouponData

@Dao
interface CouponDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoupons(coupons: CouponData)

    @Query("DELETE FROM ${Constants.COUPONTABLENAME}")
    fun deleteCoupons()

    @Query("SELECT * FROM ${Constants.COUPONTABLENAME}")
    fun getAllCoupons():LiveData<List<CouponData>>
}