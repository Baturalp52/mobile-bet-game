package com.termproject.classes

import com.google.gson.annotations.SerializedName

class FixtureResponse (
    @SerializedName("get") val get: String,
    @SerializedName("parameters") val parameters: Parameters,
    @SerializedName("errors") val errors: List<String>,
    @SerializedName("results") val results: Int,
    @SerializedName("paging") val paging: Paging,
    @SerializedName("response") val response: List<Fixture>
) {
    override fun toString(): String {
        return "FixtureResponse(get='$get', parameters=$parameters, errors=$errors, results=$results, paging=$paging, response=$response)"
    }
}