package com.termproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.termproject.databinding.ActivityMainBinding
import com.termproject.db.coupon.CouponViewModel
import com.termproject.db.user.User
import com.termproject.db.user.UserViewModel
import com.termproject.fragments.BulletinFragment
import com.termproject.fragments.CouponFragment
import com.termproject.fragments.CouponsFragment
import com.termproject.ui.CreditDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : FragmentActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var couponViewModel: CouponViewModel
    private lateinit var creditDialog: CreditDialog
    lateinit var existingUser: User

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        CoroutineScope(Dispatchers.Main).launch {
            existingUser = userViewModel.getUser()

            if (existingUser == null) {
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

        userViewModel = ViewModelProvider(this)?.get(UserViewModel::class.java)!!
        couponViewModel = ViewModelProvider(this)?.get(CouponViewModel::class.java)!!





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

        creditDialog = CreditDialog(this, userViewModel, binding.creditsButton)

        binding.creditsButton.setOnClickListener {
            creditDialog.show()
        }
    }


    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}