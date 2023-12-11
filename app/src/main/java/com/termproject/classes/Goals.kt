package com.termproject.classes

import com.google.gson.annotations.SerializedName

class Goals(
    @SerializedName("home") val home: Int,
    @SerializedName("away") val away: Int
) {
    override fun toString(): String {
        return "Goals(home=$home, away=$away)"
    }

}
