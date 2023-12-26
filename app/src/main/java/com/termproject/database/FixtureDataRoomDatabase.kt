package com.termproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.termproject.Constants
import com.termproject.classes.FixtureData

@Database(
    entities = [FixtureData::class],
    version = 2,
    exportSchema = false
)
abstract class FixtureDataRoomDatabase : RoomDatabase() {
    abstract fun showDao(): FixtureDataDAO

    companion object{
        @Volatile
        private var INSTANCE: FixtureDataRoomDatabase?=null

        fun getDatabase(context:Context): FixtureDataRoomDatabase {
            val tempInstance = INSTANCE
            if(tempInstance !=null){
                return  tempInstance
            }

            synchronized(this){
                val  instance =Room.databaseBuilder(context.applicationContext, FixtureDataRoomDatabase::class.java, Constants.DATABASENAME).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}
