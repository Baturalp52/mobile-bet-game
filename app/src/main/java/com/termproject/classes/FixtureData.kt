package com.termproject.classes

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.termproject.Constants

@Entity(tableName = Constants.TABLENAME)
class FixtureData(
    @PrimaryKey
    var data: String,
) {

}