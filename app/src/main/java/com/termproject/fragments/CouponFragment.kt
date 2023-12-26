package com.termproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.termproject.R
import com.termproject.databinding.FragmentCouponBinding


/**
 * A simple [Fragment] subclass.
 * Use the [CouponFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CouponFragment : Fragment() {
    lateinit var binding: FragmentCouponBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCouponBinding.inflate(layoutInflater)
        return binding.root
    }


}