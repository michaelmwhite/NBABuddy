package com.tmacstudios.nbabuddy.stats_screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tmacstudios.nbabuddy.R
import com.tmacstudios.nbabuddy.models.Boxscore
import com.tmacstudios.nbabuddy.models.Game
import com.tmacstudios.nbabuddy.view_models.BoxscoreViewModel
import com.tmacstudios.nbabuddy.views.TeamInfoView

const val EXTRA_GAME = "GAME"

class StatsActivity : AppCompatActivity() {
    private lateinit var hTeamInfoView: TeamInfoView
    private lateinit var vTeamInfoView: TeamInfoView
    private lateinit var recyclerView: RecyclerView
    private lateinit var statsAdapter: StatsAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var boxscoreViewModel: BoxscoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        hTeamInfoView = findViewById(R.id.h_team_info)
        vTeamInfoView = findViewById(R.id.v_team_info)
        viewManager = LinearLayoutManager(this)
        statsAdapter = StatsAdapter()
        recyclerView = findViewById<RecyclerView>(R.id.stats_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = statsAdapter
        }
        boxscoreViewModel = ViewModelProviders.of(this).get(BoxscoreViewModel::class.java)

        val game = intent.getSerializableExtra(EXTRA_GAME) as Game
        hTeamInfoView.setTeam(game.hTeam)
        vTeamInfoView.setTeam(game.vTeam)

        val boxscoreObserver = Observer<Boxscore> { boxscore ->
            statsAdapter.updateStats(boxscoreViewModel.getStatsList())
        }
        boxscoreViewModel.boxscore.observe(this, boxscoreObserver)
        boxscoreViewModel.retrieveBoxscore(game, this)
    }
}
