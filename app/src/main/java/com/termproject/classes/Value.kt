package com.termproject.classes

import com.google.gson.annotations.SerializedName

class Value(
    @SerializedName("value") val value: String,
    @SerializedName("odd") val odd: String
) {
    override fun toString(): String {
        return "Value(value='$value', odd='$odd')"
    }

}
