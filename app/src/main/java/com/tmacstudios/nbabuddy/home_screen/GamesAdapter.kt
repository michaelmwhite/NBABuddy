package com.tmacstudios.nbabuddy.home_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
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
        val game = games[position]
        holder.textView!!.text =
            game.hTeam.triCode + ": " + game.hTeam.score + " vs " + game.vTeam.triCode + ": " + game.vTeam.score
        val context = holder.textView!!.context
        val resources = context.resources
        val packageName = context.packageName
        val hImageId = resources.getIdentifier(game.hTeam.triCode.toLowerCase(), "drawable", packageName)
        val vImageId = resources.getIdentifier(game.vTeam.triCode.toLowerCase(), "drawable", packageName)
        holder.hImage!!.setImageDrawable(ContextCompat.getDrawable(context, hImageId))
        holder.vImage!!.setImageDrawable(ContextCompat.getDrawable(context, vImageId))
    }

    override fun getItemCount() = games.size

    fun updateGames(games: List<Game>) {
        this.games = games
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textView: TextView? = null
        var hImage: ImageView? = null
        var vImage: ImageView? = null

        init {
            textView = view.findViewById(R.id.game_cell_text)
            hImage = view.findViewById(R.id.h_image)
            vImage = view.findViewById(R.id.v_image)
        }
    }
}
