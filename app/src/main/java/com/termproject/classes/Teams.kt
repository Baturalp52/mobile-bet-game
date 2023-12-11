package com.termproject.classes

import com.google.gson.annotations.SerializedName

class Teams(
    @SerializedName("home") val home: TeamDetails,
    @SerializedName("away") val away: TeamDetails
) {
    override fun toString(): String {
        return "Teams(home=$home, away=$away)"
    }

}
