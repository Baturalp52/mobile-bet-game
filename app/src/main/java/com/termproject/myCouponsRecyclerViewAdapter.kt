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
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class myCouponsRecyclerViewAdapter(
    private val context: Context
) :
    RecyclerView.Adapter<myCouponsRecyclerViewAdapter.CustomRecyclerViewItemHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CustomRecyclerViewItemHolder {

        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.mycoupon_item, viewGroup, false)
        return CustomRecyclerViewItemHolder(itemView)
    }

    override fun onBindViewHolder(myRecyclerViewItemHolder: CustomRecyclerViewItemHolder, i: Int) {
        val bet = BetDetails("Fenerbahçe - Galatasaray","Bugün 19:00", "Match Result:", "2", 1.98)

        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm")
        val parser = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")


        myRecyclerViewItemHolder.couponsMatchDetails.text = bet.couponsMatchDetails
        myRecyclerViewItemHolder.couponsDate.text = bet.couponsDate
        myRecyclerViewItemHolder.couponsType.text = bet.couponsType
        myRecyclerViewItemHolder.couponsBetValue.text = bet.couponsBetValue
        myRecyclerViewItemHolder.couponsOdd.text = bet.couponsOdd.toString()

        myRecyclerViewItemHolder.parentLayout.setOnClickListener {

        }

    }

    override fun getItemCount(): Int {
        return 10 //FixturesSys.bets.size
    }


    inner class CustomRecyclerViewItemHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var couponsMatchDetails: TextView
        var couponsDate: TextView
        var couponsType: TextView
        var couponsBetValue: TextView
        var couponsOdd: TextView
        var parentLayout: LinearLayout

        init {

            couponsMatchDetails = itemView.findViewById(R.id.couponsMatchDetails)
            couponsDate = itemView.findViewById(R.id.couponsDate)
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
