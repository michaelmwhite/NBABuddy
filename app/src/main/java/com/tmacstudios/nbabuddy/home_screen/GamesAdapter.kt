package com.tmacstudios.nbabuddy.home_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tmacstudios.nbabuddy.R
import com.tmacstudios.nbabuddy.models.Game
import com.tmacstudios.nbabuddy.utils.getQuarterTime
import com.tmacstudios.nbabuddy.utils.getScoreOrTime
import com.tmacstudios.nbabuddy.views.TeamInfoView

class GamesAdapter : RecyclerView.Adapter<GamesAdapter.ViewHolder>() {
    private var games: List<Game> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.game_cell, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = games[position]
        holder.scoreTextView.text = getScoreOrTime(game)
        holder.quarterTextView.text = getQuarterTime(game)
        holder.hTeamInfo.setTeam(game.hTeam)
        holder.vTeamInfo.setTeam(game.vTeam)
    }

    override fun getItemCount() = games.size

    fun updateGames(games: List<Game>) {
        this.games = games
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var scoreTextView: TextView = view.findViewById(R.id.game_cell_score)
        var quarterTextView: TextView = view.findViewById(R.id.game_cell_quarter)
        var hTeamInfo: TeamInfoView = view.findViewById(R.id.h_team_info)
        var vTeamInfo: TeamInfoView = view.findViewById(R.id.v_team_info)
    }
}
