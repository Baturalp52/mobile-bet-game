package com.berkberaozer.hw2

import androidx.lifecycle.LiveData
import com.termproject.classes.ApiKey
import com.termproject.classes.Coupon


/*
Used to access multiple data sources. It is used to seperate code and the architecture
 */
class CouponRepository(private val couponDAO: CouponDAO) {
    val readAlldata:LiveData<List<Coupon>> = couponDAO.getAllCoupons()
    fun insertCoupon(coupon: Coupon){
        couponDAO.insertCoupon(coupon)
    }
    fun getAllCoupons(){
        couponDAO.getAllCoupons()
    }
}