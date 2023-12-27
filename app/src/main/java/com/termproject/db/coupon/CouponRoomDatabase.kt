package com.termproject.db.coupon

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.termproject.db.converters.DateConverter

@Database(
    entities = [Coupon::class, PlayedBet::class, PlayedGame::class, Team::class],
    version = 7,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class CouponRoomDatabase : RoomDatabase() {


    abstract fun couponDao(): CouponDAO

    companion object {


        @Volatile  //it makes that instance to visible to other threads
        private var INSTANCE: CouponRoomDatabase? = null

        fun getDatabase(context: Context): CouponRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            /*
            everthing in this block protected from concurrent execution by multiple threads.In this block database instance is created
            same database instance will be used. If many instance are used, it will be so expensive
             */
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CouponRoomDatabase::class.java,
                    "coupons"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }

    }
}