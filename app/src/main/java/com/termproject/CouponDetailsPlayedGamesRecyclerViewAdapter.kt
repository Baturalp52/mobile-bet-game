package com.termproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.termproject.db.coupon.CouponWithPlayedGames
import com.termproject.db.coupon.PlayedGameWithDetails
import com.termproject.utils.BetStatus
import com.termproject.utils.CouponStatus
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class CouponDetailsPlayedGamesRecyclerViewAdapter(
    private val context: Context,
    private val playedGames: List<PlayedGameWithDetails>
) :
    RecyclerView.Adapter<CouponDetailsPlayedGamesRecyclerViewAdapter.CustomRecyclerViewItemHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CustomRecyclerViewItemHolder {

        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.mycoupon_item, viewGroup, false)
        return CustomRecyclerViewItemHolder(itemView)
    }

    override fun onBindViewHolder(myRecyclerViewItemHolder: CustomRecyclerViewItemHolder, i: Int) {
        val playedGame = playedGames[i]
        val bet = BetDetails("Fenerbahçe - Galatasaray", "Bugün 19:00", "Match Result:", "2", 1.98)




        myRecyclerViewItemHolder.couponsMatchDetails.text =
            "${playedGame.homeTeam.teamName} - ${playedGame.awayTeam.teamName}"
        myRecyclerViewItemHolder.couponsType.text = playedGame.playedBet.betName
        myRecyclerViewItemHolder.couponsBetValue.text = playedGame.playedBet.betValue
        myRecyclerViewItemHolder.couponsOdd.text = String.format("%.2f", playedGame.playedBet.odd)

        if (playedGame.playedBet.status == BetStatus.LOSE) {
            myRecyclerViewItemHolder.couponsWinLose.setImageResource(R.drawable.rounded_block)
        } else if (playedGame.playedBet.status == BetStatus.PENDING) {
            myRecyclerViewItemHolder.couponsWinLose.setImageResource(R.drawable.baseline_access_time_filled_24)
        }


    }

    override fun getItemCount(): Int {
        return playedGames.size //FixturesSys.bets.size
    }


    inner class CustomRecyclerViewItemHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var couponsMatchDetails: TextView
        var couponsWinLose: ImageView
        var couponsType: TextView
        var couponsBetValue: TextView
        var couponsOdd: TextView
        var parentLayout: LinearLayout

        init {

            couponsMatchDetails = itemView.findViewById(R.id.couponsMatchDetails)
            couponsWinLose = itemView.findViewById(R.id.couponsWinLose)
            couponsType = itemView.findViewById(R.id.couponsType)
            couponsBetValue = itemView.findViewById(R.id.couponsBetValue)
            couponsOdd = itemView.findViewById(R.id.couponsOdd)
            parentLayout = itemView.findViewById(R.id.couponLinearLayout)
        }
    }

    class BetDetails(

        var couponsMatchDetails: String,
        var couponsDate: String,
        var couponsType: String,
        var couponsBetValue: String,
        var couponsOdd: Double
    )

}
