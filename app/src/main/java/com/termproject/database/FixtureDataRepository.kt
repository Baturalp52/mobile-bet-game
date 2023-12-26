package com.termproject.database

import androidx.lifecycle.LiveData
import com.termproject.classes.FixtureData


/*
Used to access multiple data sources. It is used to seperate code and the architecture
 */
class FixtureDataRepository(private val fixtureDAO: FixtureDataDAO) {
    val readAlldata:LiveData<List<FixtureData>> = fixtureDAO.getAllFixtureData()
    fun getFixtureData():FixtureData {
        return fixtureDAO.getFixtureData()
    }
    fun insertFixtureData(FixtureData: FixtureData){
        fixtureDAO.insertFixtureData(FixtureData)
    }
    fun deleteFixtureData(){
        fixtureDAO.deleteFixtureData()
    }
}