package com.termproject.classes

import com.google.gson.annotations.SerializedName

class Status(
    @SerializedName("long") val long: String,
    @SerializedName("short") val short: String,
    @SerializedName("elapsed") val elapsed: Int
) {
    override fun toString(): String {
        return "Status(long='$long', short='$short', elapsed=$elapsed)"
    }
}