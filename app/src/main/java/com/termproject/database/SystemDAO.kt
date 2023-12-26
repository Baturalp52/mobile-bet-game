package com.berkberaozer.hw2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.termproject.Constants
import com.termproject.classes.ApiKey
import com.termproject.classes.SystemVariable

@Dao
interface SystemDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVariable(systemVariable: SystemVariable)

    @Query("SELECT value FROM ${Constants.SYSTEMTABLENAME} WHERE name = 'OddsUpdate'")
    fun getOddsUpdateDate(): String

    @Query("SELECT value FROM ${Constants.SYSTEMTABLENAME} WHERE name = 'FixturesUpdate'")
    fun getFixturesUpdateDate(): String

    @Query("SELECT * FROM ${Constants.SYSTEMTABLENAME}")
    fun getAllVariables():LiveData<List<SystemVariable>>

    @Query("UPDATE ${Constants.SYSTEMTABLENAME} SET value = :date WHERE value='OddsUpdate'")
    fun setOddsUpdateDate(date: String)

    @Query("UPDATE ${Constants.SYSTEMTABLENAME} SET value = :date WHERE value='FixturesUpdate'")
    fun setFixturesUpdateDate(date: String)
}