package com.termproject.classes

import com.google.gson.annotations.SerializedName

class FixtureDetails(
    @SerializedName("id") val id: Int,
    @SerializedName("referee") val referee: String,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("date") val date: String,
    @SerializedName("timestamp") val timestamp: Long,
    @SerializedName("periods") val periods: Periods,
    @SerializedName("venue") val venue: Venue,
    @SerializedName("status") val status: Status
) {
    override fun toString(): String {
        return "FixtureDetails(id=$id, referee='$referee', timezone='$timezone', date='$date', timestamp=$timestamp, periods=$periods, venue=$venue, status=$status)"
    }

}
