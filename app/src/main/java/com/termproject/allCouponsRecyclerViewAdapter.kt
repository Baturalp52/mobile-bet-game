package com.termproject

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.time.format.DateTimeFormatter

class allCouponsRecyclerViewAdapter(
    private val context: Context
) :
    RecyclerView.Adapter<allCouponsRecyclerViewAdapter.CustomRecyclerViewItemHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CustomRecyclerViewItemHolder {

        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.all_coupons_item, viewGroup, false)
        return CustomRecyclerViewItemHolder(itemView)
    }

    override fun onBindViewHolder(myRecyclerViewItemHolder: CustomRecyclerViewItemHolder, i: Int) {
        val bet = BetDetails("111","Bugün 19:00", "2", "200", 2.08,"416")

        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm")
        val parser = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")


        myRecyclerViewItemHolder.allCouponsId.text = bet.allCouponsId
        myRecyclerViewItemHolder.allCouponsDate.text = bet.allCouponsDate
        myRecyclerViewItemHolder.allCouponsMatchCount.text = bet.allCouponsMatchCount
        myRecyclerViewItemHolder.allCouponsBetValue.text = bet.allCouponsBetValue
        myRecyclerViewItemHolder.allCouponsOdd.text = bet.allCouponsOdd.toString()
        myRecyclerViewItemHolder.allCouponsEstimatedPrice.text = bet.allCouponsEstimatedPrice

        myRecyclerViewItemHolder.allCouponLayout.setOnClickListener {
            //Log.d("ssasddasasdaddas",  "sdasadadsasddasasdsad")
            val newActivityIntent = Intent(context, CouponDetail::class.java)
            context.startActivity(newActivityIntent)
        }
    }

    override fun getItemCount(): Int {
        return 10 //FixturesSys.bets.size
    }

    inner class CustomRecyclerViewItemHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var allCouponsId: TextView
        var allCouponsDate: TextView
        var allCouponsMatchCount: TextView
        var allCouponsBetValue: TextView
        var allCouponsOdd: TextView
        var allCouponsEstimatedPrice: TextView
        var parentLayout: LinearLayout
        var allCouponLayout: LinearLayout

        init {

            allCouponsId = itemView.findViewById(R.id.allCouponsId)
            allCouponsDate = itemView.findViewById(R.id.allCouponsDate)
            allCouponsMatchCount = itemView.findViewById(R.id.allCouponsMatchCount)
            allCouponsBetValue = itemView.findViewById(R.id.allCouponsBetValue)
            allCouponsOdd = itemView.findViewById(R.id.allCouponsOdd)
            allCouponsEstimatedPrice = itemView.findViewById(R.id.allCouponsEstimatedPrice)
            parentLayout = itemView.findViewById(R.id.couponLinearLayout)
            allCouponLayout = itemView.findViewById(R.id.allCouponLayout)

        }
    }
    class BetDetails(

        var allCouponsId: String,
        var allCouponsDate: String,
        var allCouponsMatchCount: String,
        var allCouponsBetValue: String,
        var allCouponsOdd: Double,
        var allCouponsEstimatedPrice: String
    )

}
