package com.termproject.classes

import com.google.gson.annotations.SerializedName

class Score(
    @SerializedName("home") val home: Int,
    @SerializedName("away") val away: Int
) {
    override fun toString(): String {
        return "Score(home=$home, away=$away)"
    }
}