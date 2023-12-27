package com.termproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.termproject.MainActivity
import com.termproject.databinding.FragmentCouponDetailBinding
import com.termproject.db.coupon.CouponWithPlayedGames
import com.termproject.CouponDetailsPlayedGamesRecyclerViewAdapter

class CouponDetailFragment(
    val context: MainActivity,
    val coupon: CouponWithPlayedGames
) :
    Fragment() {

    private lateinit var adapter: CouponDetailsPlayedGamesRecyclerViewAdapter
    private lateinit var binding: FragmentCouponDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCouponDetailBinding.inflate(layoutInflater)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCouponDetailBinding.inflate(layoutInflater)


        val totalOdd =
            coupon.playedGames.map { it -> it.playedBet.odd }.reduce { acc, d -> acc * d }

        binding.couponsTotalOdd.text = String.format("%.2f", totalOdd)

        binding.couponsTotalAmount.text = coupon.coupon.amount.toString()

        binding.couponsMaxProfit.text = String.format("%.2f", coupon.coupon.amount * totalOdd)

        binding.matchDetailToolbar.setTitle("Coupon Details")
        binding.matchDetailToolbar.setNavigationOnClickListener {
            context.loadFragment(context.allCouponsFragment)
        }
        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.myCouponRecyclerView
        val layoutManager = LinearLayoutManager(context)

        recyclerView.layoutManager = layoutManager

        // Initialize your adapter here with proper data
        adapter = CouponDetailsPlayedGamesRecyclerViewAdapter(context, coupon.playedGames)
        recyclerView.adapter = adapter
    }
}
