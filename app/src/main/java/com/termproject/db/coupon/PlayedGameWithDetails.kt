package com.termproject.db.coupon

import androidx.room.Embedded
import androidx.room.Relation

data class PlayedGameWithDetails(
    @Embedded val playedGame: PlayedGame,
    @Relation(
        parentColumn = "playedBetId",
        entityColumn = "id"
    ) val playedBet: PlayedBet,
    @Relation(
        parentColumn = "homeTeamId",
        entityColumn = "teamId"
    ) val homeTeam: Team,
    @Relation(
        parentColumn = "awayTeamId",
        entityColumn = "teamId"
    ) val awayTeam: Team,
) {
    override fun toString(): String {
        return "PlayedGameWithDetails playedGame=$playedGame playedBet=$playedBet homeTeam=$homeTeam awayTeam=$awayTeam"
    }

}