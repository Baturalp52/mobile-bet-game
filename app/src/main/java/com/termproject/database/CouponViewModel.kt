package com.berkberaozer.hw2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.termproject.classes.ApiKey
import com.termproject.classes.Coupon
import com.termproject.classes.CouponData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
it provides data to the UI and survive configuration changes. It acts as a communication center between repository and the UI
 */
class CouponViewModel(application:Application):AndroidViewModel(application) {
    val readAllData: LiveData<List<CouponData>>
    private val repository: CouponRepository
    init {
        val couponDao= CouponRoomDatabase.getDatabase(application).couponDao()
        repository = CouponRepository(couponDao)
        readAllData = repository.readAlldata
    }
    fun insertCoupon(coupons:List<Coupon>){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.insertCoupons(coupons)
        }
    }
    fun getAllCoupons() {
        return repository.getAllCoupons()
    }
}