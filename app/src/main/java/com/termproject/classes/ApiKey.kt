package com.termproject.classes

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.termproject.Constants

@Entity(tableName = Constants.APIKEYTABLENAME)
class ApiKey(
    @PrimaryKey
    val apiKey: String,
    val usage: Int,
) {
}