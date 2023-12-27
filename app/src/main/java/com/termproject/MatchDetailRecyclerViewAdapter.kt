package com.termproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.termproject.fragments.MatchDetailFragment
import com.termproject.sys.OddsSys
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class MatchDetailRecyclerViewAdapter(
    val context: MatchDetailFragment
) :
    RecyclerView.Adapter<MatchDetailRecyclerViewAdapter.CustomRecyclerViewItemHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CustomRecyclerViewItemHolder {

        val inflater = LayoutInflater.from(context.requireContext())
        val itemView = inflater.inflate(R.layout.bet_item, viewGroup, false)
        return CustomRecyclerViewItemHolder(itemView)
    }

    override fun onBindViewHolder(myRecyclerViewItemHolder: CustomRecyclerViewItemHolder, i: Int) {


        val bet = OddsSys.bets[i]


        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm")
        val parser = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")


        val localDateTime =
            ZonedDateTime.of(
                LocalDateTime.parse(OddsSys.odd?.fixture?.date, parser),
                ZoneId.of("UTC")
            )
                .withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime()

        val date = localDateTime.format(formatter)


        myRecyclerViewItemHolder.tvBetName.text = bet.name
        val betAdapter = BetItemsRecyclerViewAdapter(context, this, bet.id, bet.name, bet.values)
        myRecyclerViewItemHolder.betRecyclerView.adapter = betAdapter
        myRecyclerViewItemHolder.betRecyclerView.layoutManager =
            GridLayoutManager(context.requireContext(), 3)


    }

    override fun getItemCount(): Int {
        return OddsSys.bets.size
    }


    inner class CustomRecyclerViewItemHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvBetName: TextView
        var betRecyclerView: RecyclerView
        var parentLayout: LinearLayout

        init {

            tvBetName = itemView.findViewById(R.id.tvBetName)
            betRecyclerView = itemView.findViewById(R.id.betRecyclerView)
            parentLayout = itemView.findViewById(R.id.betItemLinearLayout)
        }
    }


}
