package com.tmacstudios.nbabuddy.stats_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tmacstudios.nbabuddy.R
import com.tmacstudios.nbabuddy.models.StatPair

class StatsAdapter : RecyclerView.Adapter<StatsAdapter.ViewHolder>() {
    private var statsText: List<StatPair> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stat_cell, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryTextView.text = statsText[position].category
        holder.hTeamTextView.text = statsText[position].hTeamStat
        holder.vTeamTextView.text = statsText[position].vTeamStat
    }

    override fun getItemCount() = statsText.size

    fun updateStats(statsText: List<StatPair>) {
        this.statsText = statsText
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var categoryTextView: TextView = view.findViewById(R.id.stat_category)
        var hTeamTextView: TextView = view.findViewById(R.id.stat_h_team)
        var vTeamTextView: TextView = view.findViewById(R.id.stat_v_team)
    }
}