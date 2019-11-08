package com.tmacstudios.nbabuddy.home_screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        updateGames()
    }

    fun updateGames() {
        GlobalScope.launch {
            val games = loadGames(Calendar.getInstance())
            withContext(Dispatchers.Main) {
                gamesAdapter.updateGames(games)
            }
        }
    }
}
