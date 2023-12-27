package com.termproject

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.termproject.databinding.ActivityMainBinding
import com.termproject.db.coupon.CouponViewModel
import com.termproject.db.user.User
import com.termproject.db.user.UserViewModel
import com.termproject.fragments.BulletinFragment
import com.termproject.fragments.AllCouponsFragment
import com.termproject.fragments.CouponFragment
import com.termproject.ui.CreditDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MainActivity : FragmentActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel
    lateinit var couponViewModel: CouponViewModel
    private lateinit var creditDialog: CreditDialog
    var existingUser: User = User(name = "New", surname = "User", city = "",district = "",team = "")
    lateinit var mediaPlayer: MediaPlayer
    lateinit var bulletinFragment: BulletinFragment
    lateinit var couponFragment: CouponFragment
    lateinit var allCouponsFragment: AllCouponsFragment

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        CoroutineScope(Dispatchers.Main).launch {
            val user = userViewModel.getUser()

            if (user == null) {
                existingUser = User(
                    name = "New",
                    surname = "User",
                    city = "",
                    district = "",
                    team = ""
                )
                existingUser.credit = 1000
                userViewModel.createNewUser(existingUser)
            } else {
                existingUser = user
            }
            binding.profileButton.text = "${existingUser.name} ${existingUser.surname}"

            binding.creditsButton.text = existingUser.credit.toString()


        }


        return super.onCreateView(name, context, attrs)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.freed_from_desire)
        mediaPlayer.start()


        userViewModel = ViewModelProvider(this)?.get(UserViewModel::class.java)!!
        couponViewModel = ViewModelProvider(this)?.get(CouponViewModel::class.java)!!

        bulletinFragment = BulletinFragment(this, couponViewModel)
        couponFragment = CouponFragment(this, couponViewModel, userViewModel)
        allCouponsFragment = AllCouponsFragment(couponViewModel)



        loadFragment(bulletinFragment)

        val workRequest = PeriodicWorkRequestBuilder<OddCheckerWorker>(1, TimeUnit.DAYS).build()
        WorkManager.getInstance(this).enqueue(workRequest)



        binding.bottomNav.setOnItemSelectedListener { it ->
            when (it.itemId) {
                R.id.bulletin -> {
                    loadFragment(bulletinFragment)
                    true
                }

                R.id.coupon -> {
                    loadFragment(couponFragment)
                    true
                }

                R.id.all_coupons -> {
                    loadFragment(allCouponsFragment)
                    true
                }

                else -> {
                    loadFragment(bulletinFragment)
                    true
                }
            }
        }

        binding.profileButton.setOnClickListener {
            val newActivityIntent = Intent(this, ProfileActivity::class.java)
            startActivity(newActivityIntent)
        }

        creditDialog = CreditDialog(this, userViewModel, binding.creditsButton)

        binding.creditsButton.setOnClickListener {
            creditDialog.show()
        }
    }

    fun updateBadge() {

        val badge = binding.bottomNav.getOrCreateBadge(R.id.coupon)
        if (couponViewModel.coupon.playedGames.size > 0) {
            badge.isVisible = true
            badge.number = couponViewModel.coupon.playedGames.size
        } else {
            badge.isVisible = false
        }

    }

    fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}