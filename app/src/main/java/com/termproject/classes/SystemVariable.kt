package com.termproject.classes

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.termproject.Constants

@Entity(tableName = Constants.SYSTEMTABLENAME)
class SystemVariable(
    @PrimaryKey
    var name:String,
    var value: String? = null
) {

}