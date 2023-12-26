package com.termproject.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.termproject.Constants
import com.termproject.classes.FixtureData

@Dao
interface FixtureDataDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFixtureData(show: FixtureData)

    @Query("DELETE FROM ${Constants.TABLENAME}")
    fun deleteFixtureData()

    @Query("SELECT * FROM ${Constants.TABLENAME} ORDER BY data ASC")
    fun getFixtureData(): FixtureData

    @Query("SELECT * FROM ${Constants.TABLENAME} ORDER BY data ASC")
    fun getAllFixtureData():LiveData<List<FixtureData>>
}