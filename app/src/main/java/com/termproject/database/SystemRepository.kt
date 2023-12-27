package com.berkberaozer.hw2

import androidx.lifecycle.LiveData
import com.termproject.classes.SystemVariable

class SystemRepository(private val systemDAO: SystemDAO) {
    val readAlldata:LiveData<List<SystemVariable>> = systemDAO.getAllVariables()
    fun insertVariable(variable: SystemVariable){
        systemDAO.insertVariable(variable)
    }

    fun getOddsUpdateDate() : String{
        return systemDAO.getOddsUpdateDate()
    }

    fun getFixturesUpdateDate() : String {
        return systemDAO.getFixturesUpdateDate()
    }

    fun setOddsUpdateDate(date: String) {
        systemDAO.setOddsUpdateDate(date)
    }

    fun setFixturesUpdateDate(date: String){
        systemDAO.setFixturesUpdateDate(date)
    }
}