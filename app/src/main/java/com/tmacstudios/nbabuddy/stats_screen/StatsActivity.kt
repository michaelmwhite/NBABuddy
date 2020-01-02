package com.tmacstudios.nbabuddy.stats_screen

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tmacstudios.nbabuddy.R
import com.tmacstudios.nbabuddy.models.Boxscore
import com.tmacstudios.nbabuddy.models.Game
import com.tmacstudios.nbabuddy.view_models.BoxscoreViewModel
import com.tmacstudios.nbabuddy.views.TeamInfoView

const val EXTRA_GAME = "GAME"

class StatsActivity : AppCompatActivity() {
    private lateinit var hTeamInfoView: TeamInfoView
    private lateinit var vTeamInfoView: TeamInfoView
    private lateinit var boxscoreViewModel: BoxscoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        hTeamInfoView = findViewById(R.id.h_team_info)
        vTeamInfoView = findViewById(R.id.v_team_info)
        boxscoreViewModel = ViewModelProviders.of(this).get(BoxscoreViewModel::class.java)

        val game = intent.getSerializableExtra(EXTRA_GAME) as Game
        hTeamInfoView.setTeam(game.hTeam)
        vTeamInfoView.setTeam(game.vTeam)
        val boxscoreObserver = Observer<Boxscore> { boxscore ->
            Toast.makeText(
                this@StatsActivity,
                "${boxscore.basicGameData.hTeam.triCode} had ${boxscore.stats.hTeam.totals.fgm} fgm",
                Toast.LENGTH_SHORT
            ).show()
        }
        boxscoreViewModel.boxscore.observe(this, boxscoreObserver)
        boxscoreViewModel.retrieveBoxscore(game, this)
    }
}
