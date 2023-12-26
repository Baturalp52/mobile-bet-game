package com.termproject

object Constants {
    const val TABLENAME="fixtures"
    const val DATABASENAME="fixturesDB"
    const val APIKEYTABLENAME="apikeys"
    const val APIKEYDATABASENAME="apikeysDB"
    const val COUPONTABLENAME="coupons"
    const val COUPONDATABASENAME="couponsDB"
    var baseUrl: String = "https://v3.football.api-sports.io/"
    var baseUrlForImage: String = "https://media-4.api-sports.io/"
    var leagues: ArrayList<Int> = arrayListOf(39, 78, 203)
}