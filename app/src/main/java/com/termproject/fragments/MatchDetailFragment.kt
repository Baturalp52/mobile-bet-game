package com.termproject.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.termproject.MainActivity
import com.termproject.MatchDetailRecyclerViewAdapter
import com.termproject.R
import com.termproject.databinding.FragmentMatchDetailBinding
import com.termproject.db.coupon.CouponViewModel
import com.termproject.db.coupon.PlayedBet
import com.termproject.db.coupon.PlayedGame
import com.termproject.db.coupon.PlayedGameWithDetails
import com.termproject.db.coupon.Team
import com.termproject.sys.OddsSys

class MatchDetailFragment(
    val couponViewModel: CouponViewModel,
    val fixtureId: Int,
    val homeTeam: Team,
    val awayTeam: Team
) : Fragment() {

    lateinit var binding: FragmentMatchDetailBinding
    var selectedBet: PlayedBet? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMatchDetailBinding.inflate(layoutInflater)

        val playedGame = couponViewModel.coupon.playedGames.find {
            it.homeTeam.teamId == homeTeam.teamId && it.awayTeam.teamId == awayTeam.teamId
        }


        binding.matchDetailToolbar.title = "${homeTeam.teamName} - ${awayTeam.teamName}"
        binding.matchDetailToolbar.setNavigationOnClickListener {
            (context as MainActivity).loadFragment((context as MainActivity).bulletinFragment)
        }

        if (playedGame != null)
            selectedBet = playedGame!!.playedBet

        val adapter = MatchDetailRecyclerViewAdapter(this)

        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager!!.orientation = LinearLayoutManager.VERTICAL

        binding.betsRecyclerView.adapter = adapter
        binding.betsRecyclerView.layoutManager = layoutManager

        OddsSys.prepareData(adapter, fixtureId!!)

        return binding.root
    }

    fun playBet(playedBet: PlayedBet) {

        couponViewModel.addGame(
            PlayedGameWithDetails(
                playedGame = PlayedGame(
                    fixtureId = fixtureId!!.toLong()
                ),
                playedBet = playedBet,
                homeTeam = homeTeam,
                awayTeam = awayTeam
            )
        )
        (context as MainActivity).updateBadge()

    }


}