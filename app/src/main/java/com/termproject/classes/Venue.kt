package com.termproject.classes

import com.google.gson.annotations.SerializedName

class Venue(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("city") val city: String
) {
    override fun toString(): String {
        return "Venue(id=$id, name='$name', city='$city')"
    }

}
