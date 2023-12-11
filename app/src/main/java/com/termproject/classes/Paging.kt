package com.termproject.classes

import com.google.gson.annotations.SerializedName

class Paging(
    @SerializedName("current") val current: Int,
    @SerializedName("total") val total: Int
) {
    override fun toString(): String {
        return "Paging(current=$current, total=$total)"
    }

}
