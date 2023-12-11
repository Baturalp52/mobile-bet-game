package com.termproject.classes

import com.google.gson.annotations.SerializedName

class TeamDetails(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("logo") val logo: String,
    @SerializedName("winner") val winner: Boolean?
) {
    override fun toString(): String {
        return "TeamDetails(id=$id, name='$name', logo='$logo', winner=$winner)"
    }

}
