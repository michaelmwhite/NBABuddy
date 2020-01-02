package com.tmacstudios.nbabuddy.stats_screen

import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tmacstudios.nbabuddy.R

class StatsAdapter : RecyclerView.Adapter<StatsAdapter.ViewHolder>() {
    private var statsText: List<Spanned> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stat_cell, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = statsText[position]
    }

    override fun getItemCount() = statsText.size

    fun updateStats(statsText: List<Spanned>) {
        this.statsText = statsText
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textView: TextView = view.findViewById(R.id.stat_text_view)
    }
}