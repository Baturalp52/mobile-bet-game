package com.termproject.classes

import com.google.gson.annotations.SerializedName

class Bet(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("values") val values: List<Value>
) {
    override fun toString(): String {
        return "Bet(id=$id, name='$name', values=$values)"
    }

}
