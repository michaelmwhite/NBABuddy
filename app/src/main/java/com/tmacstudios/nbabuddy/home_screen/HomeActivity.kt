package com.tmacstudios.nbabuddy.home_screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tmacstudios.nbabuddy.R
import com.tmacstudios.nbabuddy.utils.loadGames
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class HomeActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var gamesAdapter: GamesAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var calendar = Calendar.getInstance()

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

        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh)
        swipeRefreshLayout.setOnRefreshListener {
            // TODO: date increment here for testing only, remove
            calendar.add(Calendar.DATE, 1)
            updateGames()
            swipeRefreshLayout.isRefreshing = false
        }

        updateGames()
    }

    private fun updateGames() {
        GlobalScope.launch {
            val games = loadGames(calendar)
            withContext(Dispatchers.Main) {
                gamesAdapter.updateGames(games)
            }
        }
    }
}
