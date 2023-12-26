package com.berkberaozer.hw2

import androidx.lifecycle.LiveData
import com.termproject.classes.SystemVariable

class SystemRepository(private val systemDAO: SystemDAO) {
    val readAlldata:LiveData<List<SystemVariable>> = systemDAO.getAllVariables()
    fun insertVariable(variable: SystemVariable){
        systemDAO.insertVariable(variable)
    }

    fun getOddsUpdateDate() {
        systemDAO.getOddsUpdateDate()
    }

    fun getFixturesUpdateDate() {
        systemDAO.getFixturesUpdateDate()
    }

    fun setOddsUpdateDate(date: String) {
        systemDAO.setOddsUpdateDate(date)
    }

    fun setFixturesUpdateDate(date: String){
        systemDAO.setFixturesUpdateDate(date)
    }
}