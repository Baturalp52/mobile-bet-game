package com.termproject.classes

import com.google.gson.annotations.SerializedName

class Parameters(
    @SerializedName("season") val season: String,
    @SerializedName("league") val league: String
) {
    override fun toString(): String {
        return "Parameters(season='$season', league='$league')"
    }
}