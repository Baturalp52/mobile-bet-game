package com.termproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.termproject.classes.Value
import com.termproject.db.coupon.PlayedBet
import com.termproject.fragments.MatchDetailFragment
import com.termproject.utils.BetStatus

class BetItemsRecyclerViewAdapter(
    private val context: MatchDetailFragment,
    private val parentAdapter: MatchDetailRecyclerViewAdapter,
    val betId: Int,
    val betName: String,
    val betValues: List<Value>
) :
    RecyclerView.Adapter<BetItemsRecyclerViewAdapter.CustomRecyclerViewItemHolder>() {
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): CustomRecyclerViewItemHolder {
        val inflater = LayoutInflater.from(context.requireContext())
        val itemView = inflater.inflate(R.layout.odd_value_item, viewGroup, false)
        return CustomRecyclerViewItemHolder(itemView)
    }

    override fun getItemCount(): Int {
        return betValues.size
    }

    override fun onBindViewHolder(
        holder: CustomRecyclerViewItemHolder,
        position: Int
    ) {
        val odd = betValues[position]

        holder.tvOddName.text = odd.value
        holder.btnOddSelect.text = String.format("%.2f", odd.odd.toFloat())

        if (context.selectedBet != null) {
            holder.btnOddSelect.isEnabled =
                context!!.selectedBet!!.betId != betId || context!!.selectedBet!!.betValue != odd.value
        } else {
            holder.btnOddSelect.isEnabled = true
        }
        holder.btnOddSelect.setOnClickListener {
            if (context.selectedBet != null)
                parentAdapter.context.couponViewModel.removeLast()

            parentAdapter.context.playBet(
                PlayedBet(
                    betId = betId,
                    betValue = odd.value,
                    betName = betName,
                    odd = odd.odd.toDouble(),
                    status = BetStatus.PENDING
                )
            )
            context.selectedBet = PlayedBet(
                betId = betId.toInt(),
                betValue = odd.value,
                betName = betName,
                odd = odd.odd.toDouble(),
                status = BetStatus.PENDING
            )
            notifyDataSetChanged()
            parentAdapter.notifyDataSetChanged()
        }

    }

    inner class CustomRecyclerViewItemHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvOddName: TextView
        var btnOddSelect: Button

        init {
            tvOddName = itemView.findViewById(R.id.tvOddName)
            btnOddSelect = itemView.findViewById(R.id.btnOddSelect)
        }
    }
}