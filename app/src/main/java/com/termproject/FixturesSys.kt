package com.termproject

import android.util.Log
import android.widget.Toast
import com.termproject.classes.Fixture
import com.termproject.classes.FixtureResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Collections


object FixturesSys {
    var fixtures: ArrayList<Fixture> = ArrayList()


    fun prepareData(adapter: FixturesRecyclerViewAdapter) {
        fixtures = ArrayList()

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val current = LocalDateTime.now().format(formatter)

        var getService = ApiClient.getClient().create(GetService::class.java)
        var fixturesRequest = getService.getFixtures(getApiKey(), "2023", current) //203 = Süper Lig


        fixturesRequest.enqueue(object : Callback<FixtureResponse> {
            override fun onFailure(call: Call<FixtureResponse>, t: Throwable) {
                Log.e("REQUEST_ERROR", t.message.toString())
            }

            override fun onResponse(
                call: Call<FixtureResponse>,
                response: Response<FixtureResponse>
            ) {
                Log.d("FETCH", "FETCHING")
                if (response.isSuccessful) {
                    fixtures = ArrayList(response.body()?.response)
                    Log.d("FETCH", fixtures.toString())
                    adapter.notifyDataSetChanged()

                }
            }
        })


    }


    private fun getApiKey(): String {  //will return one of the available api keys
        return "2513a1a8f6651498fa33fa0a17d7c215"
    }
}