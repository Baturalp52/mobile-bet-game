package com.termproject.classes

import com.google.gson.annotations.SerializedName

class Odds(
    @SerializedName("league") val league: League,
    @SerializedName("fixture") val fixture: FixtureOdd,
    @SerializedName("update") val update: String,
    @SerializedName("bookmakers") val bookmakers: List<Bookmaker>
) {
    override fun toString(): String {
        return "Odds(league=$league, fixture=$fixture, update='$update', bookmakers=$bookmakers)"
    }

}
