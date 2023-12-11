package com.termproject.classes

import com.google.gson.annotations.SerializedName

class OddResponse(
    @SerializedName("get") val get: String,
    @SerializedName("parameters") val parameters: Parameters,
    @SerializedName("errors") val errors: List<String>,
    @SerializedName("results") val results: Int,
    @SerializedName("paging") val paging: Paging,
    @SerializedName("response") val response: List<Odds>
) {
    override fun toString(): String {
        return "OddResponse(get='$get', parameters=$parameters, errors=$errors, results=$results, paging=$paging, response=$response)"
    }
}