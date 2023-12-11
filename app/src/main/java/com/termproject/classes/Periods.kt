package com.termproject.classes

import com.google.gson.annotations.SerializedName

class Periods(
    @SerializedName("first") val first: Long,
    @SerializedName("second") val second: Long
) {
    override fun toString(): String {
        return "Periods(first=$first, second=$second)"
    }

}
