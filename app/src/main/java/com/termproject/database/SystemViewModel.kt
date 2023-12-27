package com.termproject.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.berkberaozer.hw2.SystemRepository
import com.berkberaozer.hw2.SystemRoomDatabase
import com.termproject.classes.FixtureData
import com.termproject.classes.SystemVariable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
it provides data to the UI and survive configuration changes. It acts as a communication center between repository and the UI
 */
class SystemViewModel(application:Application):AndroidViewModel(application) {
    val readAllData: LiveData<List<SystemVariable>>
    private val repository: SystemRepository
    init {
        val systemDAO= SystemRoomDatabase.getDatabase(application).systemDao()
        repository = SystemRepository(systemDAO)
        readAllData = repository.readAlldata
    }
    fun insertVariable(variable: SystemVariable){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.insertVariable(variable)
        }
    }

    fun getOddsUpdateDate() : String{
        return repository.getOddsUpdateDate()
    }

    fun getFixturesUpdateDate() : String{
        return repository.getFixturesUpdateDate()
    }

    fun setOddsUpdate(date: String) {
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.setOddsUpdateDate(date)
        }
    }

    fun setFixturesUpdate(date: String){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.setFixturesUpdateDate(date)
        }
    }
}