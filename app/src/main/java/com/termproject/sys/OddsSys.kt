package com.termproject.sys

import android.util.Log
import com.termproject.ApiClient
import com.termproject.Constants
import com.termproject.FixturesRecyclerViewAdapter
import com.termproject.GetService
import com.termproject.MatchDetailRecyclerViewAdapter
import com.termproject.classes.Bet
import com.termproject.classes.Fixture
import com.termproject.classes.FixtureResponse
import com.termproject.classes.OddResponse
import com.termproject.classes.Odds
import com.termproject.classes.Value
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


class OddsSys {


    companion object {
        var bets: MutableList<Bet> = ArrayList()
        var odd: Odds? = null
        var selectedBetValue: Value? = null
        fun prepareData(adapter: MatchDetailRecyclerViewAdapter, fixtureId: Int) {
            bets = ArrayList()

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val current = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC"))
                .withZoneSameInstant(ZoneId.of("UTC+3")).toLocalDateTime().format(formatter)


            var getService = ApiClient.getClient().create(GetService::class.java)
            var fixturesRequest =
                getService.getOdds(Constants.apiKey, fixtureId.toString())


            fixturesRequest.enqueue(object : Callback<OddResponse> {
                override fun onFailure(call: Call<OddResponse>, t: Throwable) {
                    Log.e("REQUEST_ERROR", t.message.toString())
                }

                override fun onResponse(
                    call: Call<OddResponse>,
                    response: Response<OddResponse>
                ) {

                    if (response.isSuccessful) {

                        odd = response.body()?.response?.get(0)!!

                        val bookmaker = odd!!.bookmakers[0]


                        val filteredBets = bookmaker.bets.filter { it ->
                            Constants.bets.contains(it.id)
                        }.toMutableList()

                        filteredBets.forEach {
                            bets.add(it)
                        }



                        adapter.notifyDataSetChanged()

                    }
                }
            })


        }


    }


}