package com.berkberaozer.hw2

import androidx.lifecycle.LiveData
import com.termproject.classes.ApiKey


/*
Used to access multiple data sources. It is used to seperate code and the architecture
 */
class ApiKeyRepository(private val apikeyDAO: ApiKeyDAO) {
    val readAlldata:LiveData<List<ApiKey>> = apikeyDAO.getAllApiKeys()
    fun getApiKey():String {
        return apikeyDAO.getApiKey()
    }
    fun insertApiKey(apiKey: ApiKey){
        apikeyDAO.insertApiKey(apiKey)
    }
    fun setUsagesZero(){
        apikeyDAO.setUsagesZero()
    }
}