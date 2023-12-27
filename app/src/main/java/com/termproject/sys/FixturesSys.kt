package com.termproject.sys

import android.util.Log
import com.termproject.ApiClient
import com.termproject.Constants
import com.termproject.FixturesRecyclerViewAdapter
import com.termproject.GetService
import com.termproject.classes.Fixture
import com.termproject.classes.FixtureResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


class FixturesSys {


    companion object {
        var fixtures: MutableList<Fixture> = ArrayList()
        fun prepareData(adapter: FixturesRecyclerViewAdapter) {
            fixtures = ArrayList()

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val current = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC"))
                .withZoneSameInstant(ZoneId.of("UTC+3")).toLocalDateTime().format(formatter)


            var getService = ApiClient.getClient().create(GetService::class.java)
            var fixturesRequest =
                getService.getFixtures(Constants.apiKey, "2023", current) //203 = Süper Lig


            fixturesRequest.enqueue(object : Callback<FixtureResponse> {
                override fun onFailure(call: Call<FixtureResponse>, t: Throwable) {
                    Log.e("REQUEST_ERROR", t.message.toString())
                }

                override fun onResponse(
                    call: Call<FixtureResponse>,
                    response: Response<FixtureResponse>
                ) {

                    if (response.isSuccessful) {
                        fixtures = ArrayList(response.body()?.response).filter { it ->
                            if (Constants.leagues.contains(it.league.id))
                                Log.d("FIXTURE", "$it")
                            Constants.leagues.contains(it.league.id)
                        }.toMutableList()

                        adapter.notifyDataSetChanged()

                    }
                }
            })


        }


    }


}