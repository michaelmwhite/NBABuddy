package com.tmacstudios.nbabuddy.home_screen

import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tmacstudios.nbabuddy.R
import com.tmacstudios.nbabuddy.models.Game
import com.tmacstudios.nbabuddy.retrofit_apis.NBAApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var gamesAdapter: GamesAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        gamesAdapter = GamesAdapter()
        recyclerView = findViewById<RecyclerView>(R.id.games_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = gamesAdapter
        }

        GlobalScope.launch {
            val games = loadGames()
            withContext(Dispatchers.Main) {
                gamesAdapter.updateGames(games)
            }
        }
    }

    private suspend fun loadGames(): List<Game> {
        val calendar = Calendar.getInstance()
        val retrofit = Retrofit.Builder().baseUrl("http://data.nba.net/10s/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val nbaService = retrofit.create(NBAApi::class.java)
        val response = nbaService.getScoreboard(
            String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH)),
            String.format("%02d", calendar.get(Calendar.MONTH) + 1),
            String.format("%04d", calendar.get(Calendar.YEAR))
        )
        if (response.isSuccessful) {
            response.body()?.games?.map {
                Log.d(this.javaClass.simpleName, it.hTeam.triCode + " vs " + it.vTeam.triCode)
            }
            return response.body()!!.games
        }
        return emptyList()
    }
}
