package com.tmacstudios.nbabuddy.stats_screen

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tmacstudios.nbabuddy.R
import com.tmacstudios.nbabuddy.models.Game
import com.tmacstudios.nbabuddy.utils.loadBoxscore
import com.tmacstudios.nbabuddy.views.TeamInfoView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val EXTRA_GAME = "GAME"

class StatsActivity : AppCompatActivity() {
    private lateinit var hTeamInfoView: TeamInfoView
    private lateinit var vTeamInfoView: TeamInfoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        hTeamInfoView = findViewById(R.id.h_team_info)
        vTeamInfoView = findViewById(R.id.v_team_info)

        val game = intent.getSerializableExtra(EXTRA_GAME) as Game
        hTeamInfoView.setTeam(game.hTeam)
        vTeamInfoView.setTeam(game.vTeam)

        GlobalScope.launch {
            try {
                val boxscore = loadBoxscore(game)!!
                withContext((Dispatchers.Main)) {
                    Toast.makeText(
                        this@StatsActivity,
                        "${boxscore.basicGameData.hTeam.triCode} had ${boxscore.stats.hTeam.totals.fgm} fgm",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                withContext((Dispatchers.Main)) {
                    Toast.makeText(
                        this@StatsActivity,
                        "Unable to get the box score for this game.",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            }
        }

    }
}
