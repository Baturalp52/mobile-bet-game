package com.berkberaozer.hw2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.termproject.classes.ApiKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
it provides data to the UI and survive configuration changes. It acts as a communication center between repository and the UI
 */
class ApiKeyViewModel(application:Application):AndroidViewModel(application) {
    val readAllData: LiveData<List<ApiKey>>
    private val repository: ApiKeyRepository
    init {
        val showDAO= ApiKeyRoomDatabase.getDatabase(application).showDao()
        repository = ApiKeyRepository(showDAO)
        readAllData = repository.readAlldata
    }
    fun insertApiKey(apiKey:ApiKey){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.insertApiKey(apiKey)
        }
    }
    fun getApiKey():String{
        return repository.getApiKey()
    }
    fun setUsagesZero(){
        return repository.setUsagesZero()
    }
}