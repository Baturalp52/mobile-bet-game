package com.berkberaozer.hw2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.termproject.Constants
import com.termproject.classes.ApiKey
import com.termproject.classes.Coupon
import com.termproject.classes.CouponData

@Database(
    entities = [CouponData::class],
    version = 2,
    exportSchema = false
)
abstract class CouponRoomDatabase : RoomDatabase() {
    abstract fun couponDao(): CouponDAO

    companion object{
        @Volatile
        private var INSTANCE:CouponRoomDatabase?=null

        fun getDatabase(context:Context):CouponRoomDatabase{
            val tempInstance = INSTANCE
            if(tempInstance !=null){
                return  tempInstance
            }

            synchronized(this){
                val  instance =Room.databaseBuilder(context.applicationContext, CouponRoomDatabase::class.java, Constants.COUPONDATABASENAME).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}
