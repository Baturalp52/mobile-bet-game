package com.termproject.db.coupon

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.termproject.utils.BetStatus

@Entity(tableName = "played_bets")
class PlayedBet(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val betId: Int,
    val betValue: String,
    val odd: Double,
    val status: BetStatus
) {

    override fun toString(): String {
        return "PlayedBet betId=$betId betValue=$betValue odd=$odd status=$status"
    }
}