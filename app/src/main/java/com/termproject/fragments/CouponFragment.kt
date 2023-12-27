package com.termproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.termproject.allCouponsRecyclerViewAdapter
import com.termproject.databinding.FragmentAllCouponsBinding
import com.termproject.databinding.FragmentCouponsBinding

class CouponFragment : Fragment() {
    private lateinit var binding: FragmentAllCouponsBinding
    private lateinit var adapter: allCouponsRecyclerViewAdapter

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

        // Initialize your adapter here with proper data
        adapter = allCouponsRecyclerViewAdapter(requireContext())
        recyclerView.adapter = adapter
    }
}
