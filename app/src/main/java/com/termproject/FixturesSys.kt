package com.termproject

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.berkberaozer.hw2.ApiKeyViewModel
import com.berkberaozer.hw2.CouponViewModel
import com.google.gson.Gson
import com.termproject.classes.Fixture
import com.termproject.classes.FixtureResponse
import com.termproject.classes.FixturesNOdds
import com.termproject.classes.Odds
import com.termproject.database.FixtureDataViewModel
import com.termproject.database.SystemViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Collections


class FixturesSys {
    private lateinit var fixtureView: FixtureDataViewModel
    private lateinit var apiKeyView: ApiKeyViewModel
    private lateinit var couponView: com.termproject.db.coupon.CouponViewModel
    private lateinit var systemView: SystemViewModel
    var fixtures: List<Fixture> = ArrayList<Fixture>()

    //private lateinit var odds: List<Odds>

    fun prepareData(adapter: FixturesRecyclerViewAdapter) {
//
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val current = LocalDateTime.now().format(formatter)




        //        Log.d("JSONARRAYPARSE", "Before Request")
        //        fixturesRequest.enqueue(object : Callback<FixtureResponse> {
        //            override fun onFailure(call: Call<FixtureResponse>, t: Throwable) {
        //                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG).show()
        //                Log.d("JSONARRAYPARSE", "Error: "+t.message.toString())
        //            }
        //            override fun onResponse(call: Call<FixtureResponse>, response: Response<FixtureResponse>) {
        //                Log.d("JSONARRAYPARSE", "Response taken")
        //                if (response.isSuccessful) {
        //                    Log.d("JSONARRAYPARSE", response.body()?.response?.get(0)?.fixture.toString())
        //                    fixtures = response.body()?.response!!
        //                }
        //
        //            }
        //        })
        //        Log.d("JSONARRAYPARSE", "After Request")
        //
        //        var oddsRequest = getService.getOdds(getApiKey(), "2023", "203") //203 = Süper Lig
        //        Log.d("JSONARRAYPARSE", "Before Request")
        //        oddsRequest.enqueue(object : Callback<OddResponse> {
        //            override fun onFailure(call: Call<OddResponse>, t: Throwable) {
        //                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG).show()
        //                Log.d("JSONARRAYPARSE", "Error: "+t.message.toString())
        //            }
        //            override fun onResponse(call: Call<OddResponse>, response: Response<OddResponse>) {
        //                Log.d("JSONARRAYPARSE", "Response taken")
        //                if (response.isSuccessful) {
        //                    Log.d("JSONARRAYPARSE", response.body()?.response?.get(0)?.league.toString())
        //                    odds = response.body()?.response!!
        //                }
        //            }
        //        })
        //
        //
        //        Thread {
        //            Thread.sleep(5000)
        //            Log.d("ASDASDASDA", "THREAD START")
        //                fixtureView.deleteFixtureData()
        //                fixtureView.insertFixtureData(FixtureData(stringFromObject(FixturesNOdds(fixtures, odds))))
        //                fixtures = getObjectFromString(fixtureView.getFixtureData()).fixtures!!
        //                odds = getObjectFromString(fixtureView.getFixtureData()).odds!!
        //                Log.d("ASDASDASDA", "${fixtures}")
        //                Log.d("ASDASDASDA", "${odds}")
        //        }.start()
    }


    private fun getApiKey(): String {  //change the return parameter
        return "b939c30d3265119631fab5db9944311d"
    }

    private fun stringFromObject(list: FixturesNOdds): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    fun getObjectFromString(jsonString: String?): FixturesNOdds {
        return Gson().fromJson(jsonString, FixturesNOdds::class.java)
    }
}