package com.termproject.db.coupon

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "played_games", foreignKeys =
    [
        ForeignKey(
            entity = Coupon::class,
            parentColumns = ["id"],
            childColumns = ["couponId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PlayedBet::class,
            parentColumns = ["id"],
            childColumns = ["playedBetId"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = Team::class,
            parentColumns = ["teamId"],
            childColumns = ["homeTeamId"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = Team::class,
            parentColumns = ["teamId"],
            childColumns = ["awayTeamId"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
class PlayedGame(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val fixtureId: Long,
    var couponId: Long = 0,
    var playedBetId: Long = 0,
    var homeTeamId: Long = 0,
    var awayTeamId: Long = 0
) {
    override fun toString(): String {
        return "Played Game fixtureId=$fixtureId couponId=$couponId playedBetId=$playedBetId homeTeamId=$homeTeamId awayTeamId=$awayTeamId"
    }
}