package com.termproject.fragments

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.termproject.FixturesRecyclerViewAdapter
import com.termproject.MainActivity
import com.termproject.sys.FixturesSys
import com.termproject.databinding.FragmentBulletinBinding
import com.termproject.db.coupon.CouponViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [BulletinFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BulletinFragment(val context: MainActivity, val couponViewModel: CouponViewModel) :
    Fragment() {

    lateinit var binding: FragmentBulletinBinding
    lateinit var adapter: FixturesRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentBulletinBinding.inflate(layoutInflater)

        adapter = FixturesRecyclerViewAdapter(context, couponViewModel)

        if (FixturesSys.fixtures.size == 0) {
            FixturesSys.prepareData(adapter)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBulletinBinding.inflate(layoutInflater)

        val recyclerView = binding.fixturesRecyclerView
        val layoutManager = LinearLayoutManager(context)
        layoutManager!!.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager


        //Fill the RecyclerView


        recyclerView.adapter = adapter

        return binding.root
    }


}