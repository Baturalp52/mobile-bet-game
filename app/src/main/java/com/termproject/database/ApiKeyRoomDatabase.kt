package com.berkberaozer.hw2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.termproject.Constants
import com.termproject.classes.ApiKey

@Database(
    entities = [ApiKey::class],
    version = 2,
    exportSchema = false
)
abstract class ApiKeyRoomDatabase : RoomDatabase() {
    abstract fun showDao(): ApiKeyDAO

    companion object{
        @Volatile
        private var INSTANCE:ApiKeyRoomDatabase?=null

        fun getDatabase(context:Context):ApiKeyRoomDatabase{
            val tempInstance = INSTANCE
            if(tempInstance !=null){
                return  tempInstance
            }

            synchronized(this){
                val  instance =Room.databaseBuilder(context.applicationContext, ApiKeyRoomDatabase::class.java, Constants.APIKEYDATABASENAME).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}
