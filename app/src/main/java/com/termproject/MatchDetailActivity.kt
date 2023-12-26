package com.termproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.termproject.databinding.ActivityMatchDetailBinding

class MatchDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityMatchDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        binding.betsRecyclerView.adapter = adapter
        val extras = intent.extras
        val fixtureId = extras?.getInt("fixtureId")
        OddSys.prepareData(adapter,fixtureId.toString())
        */
    }
}