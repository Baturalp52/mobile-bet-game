package com.termproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.termproject.AllCouponsRecyclerViewAdapter
import com.termproject.databinding.FragmentAllCouponsBinding
import com.termproject.db.coupon.CouponViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllCouponsFragment(private val couponViewModel: CouponViewModel) : Fragment() {
    private lateinit var binding: FragmentAllCouponsBinding
    private lateinit var adapter: AllCouponsRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllCouponsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.allCRecyclerView
        val layoutManager = LinearLayoutManager(requireContext())

        recyclerView.layoutManager = layoutManager

        CoroutineScope(Dispatchers.Main).launch {


            // Initialize your adapter here with proper data
            adapter =
                AllCouponsRecyclerViewAdapter(
                    requireContext(),
                    couponViewModel.getAllCoupons()
                )
            recyclerView.adapter = adapter
        }


    }
}
