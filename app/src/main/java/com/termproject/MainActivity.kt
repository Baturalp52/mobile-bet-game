package com.termproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.termproject.classes.Fixture
import com.termproject.classes.FixtureResponse
import com.termproject.classes.OddResponse
import com.termproject.classes.Odds
import com.termproject.databinding.ActivityMainBinding
import com.termproject.db.user.User
import com.termproject.db.user.UserViewModel
import com.termproject.fragments.BulletinFragment
import com.termproject.fragments.CouponFragment
import com.termproject.fragments.CouponsFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : FragmentActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        CoroutineScope(Dispatchers.Main).launch {
            val existingUser = userViewModel.getUser()

            if (existingUser != null) {
                binding.profileButton.text = "${existingUser.name} ${existingUser.surname}"

                binding.creditsButton.text = existingUser.credit.toString()
            }

        }

        return super.onCreateView(name, context, attrs)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel = ViewModelProvider(this)?.get(UserViewModel::class.java)!!





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


    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}