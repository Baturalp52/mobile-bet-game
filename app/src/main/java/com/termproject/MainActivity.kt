package com.termproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.termproject.classes.Fixture
import com.termproject.classes.FixtureResponse
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URLEncoder
import java.net.URL
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lateinit var fixtureList: MutableList<Fixture>
        var getService = ApiClient.getClient().create(GetService::class.java)
        var request = getService.getFixtures(getApiKey(), "2023", "203") //203 = Süper Lig

        Log.d("JSONARRAYPARSE", "Before Request")
        request.enqueue(object : Callback<FixtureResponse> {
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
        /*If we can use retrofit-gson, delete here
        val client = OkHttpClient()

        fun run() {
            val request = Request.Builder()
                .url("https://v3.football.api-sports.io/fixtures?season=2023&league=203")
                .addHeader("x-apisports-key", getApiKey())
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                for ((name, value) in response.headers) {
                    println("$name: $value")
                }

                Log.d("RESPONSE", response.body!!.string())
            }
        }

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        executor.execute {
            //val response = sendGetRequest("fixtures", "203", "2023")
            run()

            handler.post {
                //for UI interaction
            }
        }
         */
    }

    private fun getApiKey() : String {  //will return one of the available api keys
        return "6835dc23ed526feb2f7f4332b6ebfc39"
    }
}