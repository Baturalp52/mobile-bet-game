package com.termproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.termproject.allCouponsRecyclerViewAdapter
import com.termproject.databinding.ActivityCouponDetailBinding

class CouponDetail : AppCompatActivity() {

    private lateinit var adapter: myCouponsRecyclerViewAdapter
    private lateinit var binding: ActivityCouponDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCouponDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.myCouponRecyclerView
        val layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager

        // Initialize your adapter here with proper data
        adapter = myCouponsRecyclerViewAdapter(this)
        recyclerView.adapter = adapter
    }
}
