package com.termproject.classes

import com.google.gson.annotations.SerializedName

class Bookmaker(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("bets") val bets: List<Bet>
) {
    override fun toString(): String {
        return "Bookmaker(id=$id, name='$name', bets=$bets)"
    }

}
