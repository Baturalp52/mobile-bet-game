package com.termproject.classes

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.termproject.Constants

@Entity(tableName = Constants.COUPONTABLENAME)
class CouponData (
    @PrimaryKey
    var data: String,
) {

}