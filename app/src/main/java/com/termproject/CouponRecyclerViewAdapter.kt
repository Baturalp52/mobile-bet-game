package com.termproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.termproject.db.coupon.PlayedGameWithDetails
import com.termproject.sys.OddsSys
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class CouponRecyclerViewAdapter(
    private val context: Context,
    val playedGamesWithDetails: List<PlayedGameWithDetails>
) :
    RecyclerView.Adapter<CouponRecyclerViewAdapter.CustomRecyclerViewItemHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CustomRecyclerViewItemHolder {

        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.coupon_item, viewGroup, false)
        return CustomRecyclerViewItemHolder(itemView)
    }

    override fun onBindViewHolder(myRecyclerViewItemHolder: CustomRecyclerViewItemHolder, i: Int) {
        val playedGame = playedGamesWithDetails[i]
        val bet = BetDetails("Fenerbahçe - Galatasaray", "Bugün 19:00", "Match Result:", "2", 1.98)


        myRecyclerViewItemHolder.tvMatchDetails.text =
            "${playedGame.homeTeam.teamName} - ${playedGame.awayTeam.teamName}"
        myRecyclerViewItemHolder.tvType.text = playedGame.playedBet.betName
        myRecyclerViewItemHolder.tvBetValue.text = playedGame.playedBet.betValue
        myRecyclerViewItemHolder.tvOdd.text = String.format("%.1f", playedGame.playedBet.odd)

        myRecyclerViewItemHolder.parentLayout.setOnClickListener {

        }

    }


    override fun getItemCount(): Int {
        return playedGamesWithDetails.size
    }


    inner class CustomRecyclerViewItemHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvMatchDetails: TextView

        var tvType: TextView
        var tvBetValue: TextView
        var tvOdd: TextView
        var parentLayout: LinearLayout

        init {

            tvMatchDetails = itemView.findViewById(R.id.tvMatchDetails)
            tvType = itemView.findViewById(R.id.tvType)
            tvBetValue = itemView.findViewById(R.id.tvBetValue)
            tvOdd = itemView.findViewById(R.id.tvOdd)
            parentLayout = itemView.findViewById(R.id.couponLinearLayout)
        }
    }

    class BetDetails(
        var tvMatchDetails: String,
        var tvDate: String,
        var tvType: String,
        var tvBetValue: String,
        var tvOdd: Double
    )

}
