package com.termproject

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.termproject.db.coupon.CouponViewModel
import com.termproject.db.coupon.CouponWithPlayedGames
import com.termproject.fragments.CouponDetailFragment
import com.termproject.utils.CouponStatus
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class AllCouponsRecyclerViewAdapter(
    private val context: Context,
    val coupons: List<CouponWithPlayedGames>
) :
    RecyclerView.Adapter<AllCouponsRecyclerViewAdapter.CustomRecyclerViewItemHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CustomRecyclerViewItemHolder {

        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.all_coupons_item, viewGroup, false)
        return CustomRecyclerViewItemHolder(itemView)
    }

    override fun onBindViewHolder(myRecyclerViewItemHolder: CustomRecyclerViewItemHolder, i: Int) {
        val coupon = coupons[i]


        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm")

        val localDateTime =
            ZonedDateTime.of(
                coupon.coupon.playedAt,
                ZoneId.of("UTC")
            )
                .withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime()

        val playedDate = localDateTime.format(formatter)


        val totalOdd =
            coupon.playedGames.map { it -> it.playedBet.odd }.reduce { acc, d -> acc * d }

        myRecyclerViewItemHolder.allCouponsDate.text = playedDate
        myRecyclerViewItemHolder.allCouponsMatchCount.text =
            "${coupon.playedGames.size} "
        myRecyclerViewItemHolder.allCouponsAmount.text = "${coupon.coupon.amount} "
        myRecyclerViewItemHolder.allCouponsOdd.text =
            "${String.format("%.2f", totalOdd)} "
        if (coupon.coupon.status == CouponStatus.LOSE) {
            myRecyclerViewItemHolder.allCouponsWinLose.setImageResource(R.drawable.rounded_block)
        } else if (coupon.coupon.status == CouponStatus.PENDING) {
            myRecyclerViewItemHolder.allCouponsWinLose.setImageResource(R.drawable.baseline_access_time_filled_24)
        }


        myRecyclerViewItemHolder.allCouponLayout.setOnClickListener {
            (context as MainActivity).loadFragment(
                CouponDetailFragment(
                    context,
                    coupon = coupon
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return coupons.size //FixturesSys.bets.size
    }

    inner class CustomRecyclerViewItemHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var allCouponsDate: TextView
        var allCouponsMatchCount: TextView
        var allCouponsOdd: TextView
        var allCouponsAmount: TextView
        var allCouponsWinLose: ImageView
        var parentLayout: LinearLayout
        var allCouponLayout: LinearLayout

        init {


            allCouponsDate = itemView.findViewById(R.id.allCouponsDate)
            allCouponsMatchCount = itemView.findViewById(R.id.allCouponsMatchCount)
            allCouponsAmount = itemView.findViewById(R.id.allCouponsAmount)
            allCouponsOdd = itemView.findViewById(R.id.allCouponsOdd)
            allCouponsWinLose = itemView.findViewById(R.id.allCouponsWinLose)
            parentLayout = itemView.findViewById(R.id.couponLinearLayout)
            allCouponLayout = itemView.findViewById(R.id.allCouponLayout)

        }
    }


}
