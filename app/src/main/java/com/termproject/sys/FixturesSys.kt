package com.termproject.sys

import android.util.Log
import com.termproject.ApiClient
import com.termproject.FixturesRecyclerViewAdapter
import com.termproject.GetService
import com.termproject.classes.Fixture
import com.termproject.classes.FixtureResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class FixturesSys {


    companion object {
        var fixtures: ArrayList<Fixture> = ArrayList()
        fun prepareData(adapter: FixturesRecyclerViewAdapter) {
            fixtures = ArrayList()

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val current = LocalDateTime.now().format(formatter)

            var getService = ApiClient.getClient().create(GetService::class.java)
            var fixturesRequest =
                getService.getFixtures(getApiKey(), "2023", current) //203 = Süper Lig


            fixturesRequest.enqueue(object : Callback<FixtureResponse> {
                override fun onFailure(call: Call<FixtureResponse>, t: Throwable) {
                    Log.e("REQUEST_ERROR", t.message.toString())
                }

                override fun onResponse(
                    call: Call<FixtureResponse>,
                    response: Response<FixtureResponse>
                ) {

                    if (response.isSuccessful) {
                        fixtures = ArrayList(response.body()?.response)

                        adapter.notifyDataSetChanged()

                    }
                }
            })


        }

        private fun getApiKey(): String {  //will return one of the available api keys
            return "2513a1a8f6651498fa33fa0a17d7c215"
        }
    }


}