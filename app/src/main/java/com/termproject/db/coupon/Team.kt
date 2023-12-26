package com.termproject.db.coupon

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
class Team(
    @PrimaryKey
    val teamId: Long,
    val teamName: String,
    val teamLogo: String
) {

    override fun toString(): String {
        return "Team id=$teamId logo=$teamLogo name=$teamName"
    }
}