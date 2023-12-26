package com.termproject

import android.R
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.termproject.databinding.ActivityProfileBinding
import com.termproject.db.user.User
import com.termproject.db.user.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    private lateinit var userViewModel: UserViewModel


    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        CoroutineScope(Dispatchers.Main).launch {
            val existingUser = userViewModel.getUser()

            if (existingUser != null) {
                binding.etName.setText(existingUser.name)
                binding.etSurname.setText(existingUser.surname)
                binding.etCity.setText(existingUser.city)
                binding.etTeam.setText(existingUser.team)
                binding.etDistrict.setText(existingUser.district)
            }

        }

        return super.onCreateView(name, context, attrs)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityProfileBinding.inflate(layoutInflater)

        userViewModel = ViewModelProvider(this)?.get(UserViewModel::class.java)!!

        binding.profileBtn.setOnClickListener {
            val name: String = binding.etName.text.toString()
            val surname: String = binding.etSurname.text.toString()
            val city: String = binding.etCity.text.toString()
            val district: String = binding.etDistrict.text.toString()
            val team: String = binding.etTeam.text.toString()
            CoroutineScope(Dispatchers.Main).launch {
                val existingUser = userViewModel.getUser()

                if (existingUser == null) {
                    val user = User(
                        name = name,
                        surname = surname,
                        city = city,
                        district = district,
                        team = team
                    )
                    user.credit = 1000
                    userViewModel.createNewUser(user)
                } else {
                    existingUser.team = team
                    existingUser.name = name
                    existingUser.surname = surname
                    existingUser.district = district
                    existingUser.city = city
                    userViewModel.updateUser(existingUser)
                }

            }


        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        setContentView(binding.root)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }
}