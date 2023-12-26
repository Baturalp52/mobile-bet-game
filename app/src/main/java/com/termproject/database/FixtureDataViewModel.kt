package com.termproject.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.termproject.classes.FixtureData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
it provides data to the UI and survive configuration changes. It acts as a communication center between repository and the UI
 */
class FixtureDataViewModel(application:Application):AndroidViewModel(application) {
    val readAllData: LiveData<List<FixtureData>>
    private val repository: FixtureDataRepository
    init {
        val showDAO= FixtureDataRoomDatabase.getDatabase(application).showDao()
        repository = FixtureDataRepository(showDAO)
        readAllData = repository.readAlldata
    }
    fun insertFixtureData(fixtureData:FixtureData){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.insertFixtureData(fixtureData)
        }
    }
    fun deleteFixtureData(){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.deleteFixtureData()
        }
    }
    fun getFixtureData():String{
        lateinit var data:String
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            data = repository.getFixtureData().data
        }
        return data
    }
}