package com.termproject

import android.content.Context
import android.util.Log
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

class FixturesRecyclerViewAdapter(
    private val context: Context
) :
    RecyclerView.Adapter<FixturesRecyclerViewAdapter.CustomRecyclerViewItemHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CustomRecyclerViewItemHolder {

        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.fixture_item, viewGroup, false)
        return CustomRecyclerViewItemHolder(itemView)
    }

    override fun onBindViewHolder(myRecyclerViewItemHolder: CustomRecyclerViewItemHolder, i: Int) {
        val fixture = FixturesSys.fixtures[i]

        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm")
        val parser = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")

        val localDateTime =
            ZonedDateTime.of(LocalDateTime.parse(fixture.fixture.date, parser), ZoneId.of("UTC"))
                .withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime()

        val date = localDateTime.format(formatter)

        myRecyclerViewItemHolder.tvItemHome.text = fixture.teams.home.name
        Picasso.get().load(fixture.teams.home.logo).into(myRecyclerViewItemHolder.imgItemHome)
        myRecyclerViewItemHolder.tvItemAway.text = fixture.teams.away.name
        Picasso.get().load(fixture.teams.away.logo).into(myRecyclerViewItemHolder.imgItemAway)
        myRecyclerViewItemHolder.tvMatchDetails.text =
            "${fixture.league.country} ${fixture.league.name} - ${date}"
        Picasso.get().load(fixture.league.logo).into(myRecyclerViewItemHolder.imgMatchLeague)


        myRecyclerViewItemHolder.parentLayout.setOnClickListener {

        }

    }

    override fun getItemCount(): Int {

        return FixturesSys.fixtures.size
    }


    inner class CustomRecyclerViewItemHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvItemHome: TextView
        var imgItemHome: ImageView
        var tvItemAway: TextView
        var imgItemAway: ImageView
        var tvMatchDetails: TextView
        var imgMatchLeague: ImageView
        var parentLayout: LinearLayout

        init {
            tvItemHome = itemView.findViewById(R.id.tvItemHome)
            imgItemHome = itemView.findViewById(R.id.imgItemHome)
            tvItemAway = itemView.findViewById(R.id.tvItemAway)
            imgItemAway = itemView.findViewById(R.id.imgItemAway)
            tvMatchDetails = itemView.findViewById(R.id.tvMatchDetails)
            imgMatchLeague = itemView.findViewById(R.id.imgLeagueLogo)
            parentLayout = itemView.findViewById(R.id.itemLinearLayout)
        }
    }
}