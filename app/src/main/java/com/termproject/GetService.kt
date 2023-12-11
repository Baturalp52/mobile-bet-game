package com.termproject

import com.termproject.classes.Fixture
import com.termproject.classes.FixtureResponse
import com.termproject.classes.OddResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface GetService {
    @GET("fixtures")
    fun getFixtures(@Header("x-apisports-key") apiKey: String, @Query("season") season: String, @Query("league") league: String): Call<FixtureResponse>

    @GET("odds")
    fun getOdds(@Header("x-apisports-key") apiKey: String, @Query("season") season: String, @Query("league") league: String): Call<OddResponse>
}