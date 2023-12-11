package com.termproject.classes

import com.google.gson.annotations.SerializedName

class Scores(
    @SerializedName("halftime") val halftime: Score,
    @SerializedName("fulltime") val fulltime: Score,
    @SerializedName("extratime") val extratime: Score,
    @SerializedName("penalty") val penalty: Score
) {
    override fun toString(): String {
        return "Scores(halftime=$halftime, fulltime=$fulltime, extratime=$extratime, penalty=$penalty)"
    }

}
