package com.termproject.db.coupon

import androidx.room.Embedded
import androidx.room.Relation

data class CouponWithPlayedGames(
    @Embedded
    val coupon: Coupon,
    @Relation(
        entity = PlayedGame::class,
        parentColumn = "id",
        entityColumn = "couponId"
    ) val playedGames: MutableList<PlayedGameWithDetails>
) {
    override fun toString(): String {
        return "CouponWithPlayedGames coupon=$coupon playedGames=${playedGames.joinToString("\n")}"
    }
}