package com.termproject

import android.R
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.termproject.databinding.ActivityProfileBinding
import com.termproject.db.Customer
import com.termproject.db.CustomerViewModel


class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding

    private lateinit var customerViewModel: CustomerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityProfileBinding.inflate(layoutInflater)

        customerViewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)

        binding.profileBtn.setOnClickListener {
            val name: String = binding.etName.text.toString()
            val surname: String = binding.etSurname.text.toString()
            val city: String = binding.etCity.text.toString()
            val district: String = binding.etDistrict.text.toString()
            val team: String = binding.etTeam.text.toString()

            val user = Customer(1,name,surname,city,district,team)

            customerViewModel.addCustomer(user)


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