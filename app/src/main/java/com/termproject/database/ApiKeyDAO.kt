package com.berkberaozer.hw2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.termproject.Constants
import com.termproject.classes.ApiKey

@Dao
interface ApiKeyDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertApiKey(apiKey: ApiKey)

    @Query("UPDATE ${Constants.APIKEYTABLENAME} SET usage = 0")
    fun setUsagesZero()

    @Query("SELECT apiKey FROM ${Constants.APIKEYTABLENAME} WHERE usage < 100 ORDER BY apiKey ASC")
    fun getApiKey(): String

    @Query("SELECT * FROM ${Constants.APIKEYTABLENAME} ORDER BY apiKey ASC")
    fun getAllApiKeys():LiveData<List<ApiKey>>
}