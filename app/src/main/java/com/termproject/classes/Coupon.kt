package com.termproject.classes

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.termproject.Constants

@Entity(tableName = Constants.COUPONTABLENAME)
class Coupon(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val bets: List<Bet>
) {

}