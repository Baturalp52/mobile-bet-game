package com.berkberaozer.hw2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.termproject.Constants
import com.termproject.classes.SystemVariable

@Database(
    entities = [SystemVariable::class],
    version = 2,
    exportSchema = false
)
abstract class SystemRoomDatabase : RoomDatabase() {
    abstract fun systemDao(): SystemDAO

    companion object{
        @Volatile
        private var INSTANCE:SystemRoomDatabase?=null

        fun getDatabase(context:Context):SystemRoomDatabase{
            val tempInstance = INSTANCE
            if(tempInstance !=null){
                return  tempInstance
            }

            synchronized(this){
                val  instance =Room.databaseBuilder(context.applicationContext, SystemRoomDatabase::class.java, Constants.SYSTEMDATABASENAME).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}
