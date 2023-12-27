package com.termproject.fragments

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.termproject.FixturesRecyclerViewAdapter
import com.termproject.R
import com.termproject.sys.FixturesSys
import com.termproject.databinding.FragmentBulletinBinding


/**
 * A simple [Fragment] subclass.
 * Use the [BulletinFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BulletinFragment() : Fragment() {

    lateinit var binding: FragmentBulletinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentBulletinBinding.inflate(layoutInflater)

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
        val adapter = FixturesRecyclerViewAdapter(requireContext())
        if (FixturesSys.fixtures.size == 0) {
            FixturesSys.prepareData(adapter)
        }

        recyclerView.adapter = adapter

        return binding.root
    }


}