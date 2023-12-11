package com.termproject.classes

import com.google.gson.annotations.SerializedName

class FixtureOdd(
    @SerializedName("id") val id: Int,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("date") val date: String,
    @SerializedName("timestamp") val timestamp: Int
    ) {
    override fun toString(): String {
        return "FixtureOdd(id=$id, timezone='$timezone', date='$date', timestamp=$timestamp)"
    }

}
