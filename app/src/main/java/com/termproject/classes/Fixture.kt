package com.termproject.classes

import com.google.gson.annotations.SerializedName

class Fixture(
    @SerializedName("fixture") val fixture: FixtureDetails,
    @SerializedName("league") val league: League,
    @SerializedName("teams") val teams: Teams,
    @SerializedName("goals") val goals: Goals,
    @SerializedName("score") val score: Scores
) {
    override fun toString(): String {
        return "Fixture(fixture=$fixture, league=$league, teams=$teams, goals=$goals, score=$score)"
    }

}