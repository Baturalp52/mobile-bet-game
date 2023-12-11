package com.termproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.termproject.classes.Fixture
import com.termproject.classes.FixtureResponse
import com.termproject.classes.OddResponse
import com.termproject.classes.Odds
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var getService = ApiClient.getClient().create(GetService::class.java)
        var fixturesRequest = getService.getFixtures(getApiKey(), "2023", "203") //203 = Süper Lig

        Log.d("JSONARRAYPARSE", "Before Request")
        fixturesRequest.enqueue(object : Callback<FixtureResponse> {
            override fun onFailure(call: Call<FixtureResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG).show()
                Log.d("JSONARRAYPARSE", "Error: "+t.message.toString())
            }
            override fun onResponse(call: Call<FixtureResponse>, response: Response<FixtureResponse>) {
                Log.d("JSONARRAYPARSE", "Response taken")
                if (response.isSuccessful) {
                    Log.d("JSONARRAYPARSE", response.body()?.response?.get(0)?.fixture.toString())
                }
            }
        })
        Log.d("JSONARRAYPARSE", "After Request")

        var oddsRequest = getService.getOdds(getApiKey(), "2023", "203") //203 = Süper Lig
        Log.d("JSONARRAYPARSE", "Before Request")
        oddsRequest.enqueue(object : Callback<OddResponse> {
            override fun onFailure(call: Call<OddResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG).show()
                Log.d("JSONARRAYPARSE", "Error: "+t.message.toString())
            }
            override fun onResponse(call: Call<OddResponse>, response: Response<OddResponse>) {
                Log.d("JSONARRAYPARSE", "Response taken")
                if (response.isSuccessful) {
                    Log.d("JSONARRAYPARSE", response.body()?.response?.get(0)?.league.toString())
                }
            }
        })
    }

    private fun getApiKey() : String {  //will return one of the available api keys
        return "6835dc23ed526feb2f7f4332b6ebfc39"
    }
}