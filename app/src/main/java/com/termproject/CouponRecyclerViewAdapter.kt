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

class CouponRecyclerViewAdapter(
    private val context: Context
) :
    RecyclerView.Adapter<CouponRecyclerViewAdapter.CustomRecyclerViewItemHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CustomRecyclerViewItemHolder {

        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.coupon_item, viewGroup, false)
        return CustomRecyclerViewItemHolder(itemView)
    }

    override fun onBindViewHolder(myRecyclerViewItemHolder: CustomRecyclerViewItemHolder, i: Int) {
        val bet = BetDetails("Fenerbahçe - Galatasaray","Bugün 19:00", "Match Result:", "2", 1.98)


        val maxRateArr = ArrayList<Double>()
        maxRateArr.add(bet.tvOdd)
        maxRateArr.add(bet.tvOdd)
        maxRateArr.add(bet.tvOdd)






        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm")
        val parser = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")


        myRecyclerViewItemHolder.tvMatchDetails.text = bet.tvMatchDetails
        myRecyclerViewItemHolder.tvDate.text = bet.tvDate
        myRecyclerViewItemHolder.tvType.text = bet.tvType
        myRecyclerViewItemHolder.tvBetValue.text = bet.tvBetValue
        myRecyclerViewItemHolder.tvOdd.text = bet.tvOdd.toString()

        myRecyclerViewItemHolder.parentLayout.setOnClickListener {

        }

    }

    override fun getItemCount(): Int {
        return 1 //FixturesSys.bets.size
    }


    inner class CustomRecyclerViewItemHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvMatchDetails: TextView
        var tvDate: TextView
        var tvType: TextView
        var tvBetValue: TextView
        var tvOdd: TextView
        var parentLayout: LinearLayout
        init {

            tvMatchDetails = itemView.findViewById(R.id.tvMatchDetails)
            tvDate = itemView.findViewById(R.id.tvDate)
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

     fun calculateRate():Double {
        // Check if the array is not empty
         val bet = BetDetails("Fenerbahçe - Galatasaray","Bugün 19:00", "Match Result:", "2", 1.98)

         val maxRateArr = ArrayList<Double>()
         maxRateArr.add(bet.tvOdd)
         maxRateArr.add(bet.tvOdd)
         maxRateArr.add(bet.tvOdd)

         if (maxRateArr.isNotEmpty()) {
            return maxRateArr.reduce { acc, element -> acc * element }
        }
        return 0.0
    }

    fun calculateProfit(balance:Double, rate:Double):Double{
        return balance * rate
    }

}
