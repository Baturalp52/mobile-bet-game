package com.termproject.classes

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.termproject.Constants

class Coupon(
    var status: String,
    val bets: List<Bet>
) {

}