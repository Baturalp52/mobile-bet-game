package com.termproject.classes

import com.google.gson.annotations.SerializedName

class League(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String,
    @SerializedName("logo") val logo: String,
    @SerializedName("flag") val flag: String,
    @SerializedName("season") val season: Int,
    @SerializedName("round") val round: String
) {
    override fun toString(): String {
        return "League(id=$id, name='$name', country='$country', logo='$logo', flag='$flag', season=$season, round='$round')"
    }
}