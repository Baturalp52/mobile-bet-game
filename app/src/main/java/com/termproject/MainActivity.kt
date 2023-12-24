package com.termproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.termproject.classes.Fixture
import com.termproject.classes.FixtureResponse
import com.termproject.classes.OddResponse
import com.termproject.classes.Odds
import com.termproject.databinding.ActivityMainBinding
import com.termproject.fragments.BulletinFragment
import com.termproject.fragments.CouponFragment
import com.termproject.fragments.CouponsFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : FragmentActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var getService = ApiClient.getClient().create(GetService::class.java)
        var fixturesRequest = getService.getFixtures(getApiKey(), "2023", "203") //203 = Süper Lig

        Log.d("JSONARRAYPARSE", "Before Request")
        fixturesRequest.enqueue(object : Callback<FixtureResponse> {
            override fun onFailure(call: Call<FixtureResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG).show()
                Log.d("JSONARRAYPARSE", "Error: " + t.message.toString())
            }

            override fun onResponse(
                call: Call<FixtureResponse>,
                response: Response<FixtureResponse>
            ) {
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
                Log.d("JSONARRAYPARSE", "Error: " + t.message.toString())
            }

            override fun onResponse(call: Call<OddResponse>, response: Response<OddResponse>) {
                Log.d("JSONARRAYPARSE", "Response taken")
                if (response.isSuccessful) {
                    Log.d("JSONARRAYPARSE", response.body()?.response?.get(0)?.league.toString())
                }
            }
        })

        loadFragment(BulletinFragment())


        binding.bottomNav.setOnItemSelectedListener { it ->
            when (it.itemId) {
                R.id.bulletin -> {
                    loadFragment(BulletinFragment())
                    true
                }

                R.id.coupon -> {
                    loadFragment(CouponFragment())
                    true
                }

                R.id.coupons -> {
                    loadFragment(CouponsFragment())
                    true
                }

                else -> {
                    loadFragment(BulletinFragment())
                    true
                }
            }
        }

        binding.profileButton.setOnClickListener {
            val newActivityIntent = Intent(this, ProfileActivity::class.java)
            startActivity(newActivityIntent)
        }
    }

    private fun getApiKey(): String {  //will return one of the available api keys
        return "6835dc23ed526feb2f7f4332b6ebfc39"
    }


    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}