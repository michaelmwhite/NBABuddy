package com.tmacstudios.nbabuddy.home_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tmacstudios.nbabuddy.R
import com.tmacstudios.nbabuddy.models.Game

class GamesAdapter : RecyclerView.Adapter<GamesAdapter.ViewHolder>() {
    private var games: List<Game> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.game_cell, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView!!.text = games[position].startTimeEastern
    }

    override fun getItemCount() = games.size

    fun updateGames(games: List<Game>) {
        this.games = games
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textView: TextView? = null

        init {
            textView = view.findViewById(R.id.game_cell_text)
        }
    }
}
