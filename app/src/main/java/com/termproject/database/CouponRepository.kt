package com.berkberaozer.hw2

import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.termproject.classes.ApiKey
import com.termproject.classes.Coupon
import com.termproject.classes.CouponData
import com.termproject.classes.Coupons
import com.termproject.classes.FixturesNOdds


/*
Used to access multiple data sources. It is used to seperate code and the architecture
 */
class CouponRepository(private val couponDAO: CouponDAO) {
    val readAlldata:LiveData<List<CouponData>> = couponDAO.getAllCoupons()
    fun insertCoupons(coupons: List<Coupon>){
        couponDAO.insertCoupons(CouponData(stringFromObject(Coupons(coupons))))
    }

    fun getAllCoupons(){
        couponDAO.getAllCoupons()
    }

    fun deleteCoupons(){
        couponDAO.deleteCoupons()
    }

    fun getCouponList() : List<Coupon> {
        return getObjectFromString(couponDAO.getAllCoupons().value?.get(0)?.data).coupons
    }

    private fun stringFromObject(list: Coupons): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    fun getObjectFromString(jsonString: String?): Coupons {
        return Gson().fromJson(jsonString, Coupons::class.java)
    }
}