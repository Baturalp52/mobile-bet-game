package com.termproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.termproject.R
import com.termproject.databinding.FragmentCouponBinding
import com.termproject.databinding.FragmentCouponsBinding


/**
 * A simple [Fragment] subclass.
 * Use the [CouponsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CouponsFragment : Fragment() {
    lateinit var binding: FragmentCouponsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCouponsBinding.inflate(
            layoutInflater
        )

        return binding.root
    }


}