package com.tmacstudios.nbabuddy.home_screen

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tmacstudios.nbabuddy.R
import com.tmacstudios.nbabuddy.models.Game
import com.tmacstudios.nbabuddy.models.Team
import com.tmacstudios.nbabuddy.retrofit_apis.NBAApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var gameAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val testTeam = Team("loss", "score", "triCode", "win")
        val testGames = listOf(
            Game(testTeam, "start time eastern", testTeam),
            Game(testTeam, "start time eastern 2", testTeam),
            Game(testTeam, "start time eastern 3", testTeam)
        )

        viewManager = LinearLayoutManager(this)
        gameAdapter = GamesAdapter(testGames)
        recyclerView = findViewById<RecyclerView>(R.id.games_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = gameAdapter
        }

        loadGames()
    }

    private fun loadGames() {
        val retrofit = Retrofit.Builder().baseUrl("http://data.nba.net/10s/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val nbaService = retrofit.create(NBAApi::class.java)
        Thread {
            val response = nbaService.getScoreboard(16, 10, 2019).execute()
            if (response.isSuccessful) {
                response.body()?.games?.map {
                    Log.d(this.javaClass.simpleName, it.hTeam.triCode + " vs " + it.vTeam.triCode)
                }
            }
        }.start()
    }
}
