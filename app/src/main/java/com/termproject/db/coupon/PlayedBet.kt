package com.termproject.db.coupon

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.termproject.utils.BetStatus

@Entity(tableName = "played_bets")
class PlayedBet(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val betId: Int,
    val betValue: String,
    val betName: String,
    val odd: Double,
    var status: BetStatus
) {

    override fun toString(): String {
        return "PlayedBet betId=$betId betName=$betName betValue=$betValue odd=$odd status=$status"
    }

    override fun equals(other: Any?): Boolean {
        return betId == (other as PlayedBet).betId
    }
}