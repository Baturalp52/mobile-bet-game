package com.termproject

object Constants {
    const val TABLENAME="fixtures"
    const val DATABASENAME="fixturesDB"
    const val APIKEYTABLENAME="apikeys"
    const val APIKEYDATABASENAME="apikeysDB"
    const val COUPONTABLENAME="coupons"
    const val COUPONDATABASENAME="couponsDB"
    const val SYSTEMTABLENAME="system"
    const val SYSTEMDATABASENAME="systemDB"
    var baseUrl: String = "https://v3.football.api-sports.io/"
    var baseUrlForImage: String = "https://media-4.api-sports.io/"
    var leagues: ArrayList<Int> =
        arrayListOf(4, 21, 61, 71, 39, 78, 135, 88, 140, 2, 203, 206, 66, 63)
    var bets: List<Int> = listOf(1, 5, 8, 10)
    val apiKey = "2513a1a8f6651498fa33fa0a17d7c215"
}