package com.tmacstudios.nbabuddy.home_screen

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tmacstudios.nbabuddy.R
import com.tmacstudios.nbabuddy.utils.formatDate
import com.tmacstudios.nbabuddy.utils.loadGames
import com.tmacstudios.nbabuddy.view_models.CalendarViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class HomeActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var gamesAdapter: GamesAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var dateTextView: TextView
    private lateinit var emptyTextView: TextView
    private lateinit var calendarViewModel: CalendarViewModel

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
            updateGames(calendarViewModel.calendar.value!!)
            swipeRefreshLayout.isRefreshing = false
        }

        dateTextView = findViewById(R.id.date_text_view)
        emptyTextView = findViewById(R.id.empty_text_view)

        calendarViewModel = ViewModelProviders.of(this).get(CalendarViewModel::class.java)
        val dateObserver = Observer<Calendar> { calendar ->
            updateGames(calendar)
            dateTextView.text = formatDate(calendar)
        }
        calendarViewModel.calendar.observe(this, dateObserver)
        calendarViewModel.calendar.value = Calendar.getInstance()
    }

    fun leftArrowClick(view: View) {
        val calendar = calendarViewModel.calendar.value!!
        calendar.add(Calendar.DATE, -1)
        calendarViewModel.calendar.value = calendar
    }

    fun rightArrowClick(view: View) {
        val calendar = calendarViewModel.calendar.value!!
        calendar.add(Calendar.DATE, 1)
        calendarViewModel.calendar.value = calendar
    }

    private fun updateGames(calendar: Calendar) {
        GlobalScope.launch {
            try {
                val games = loadGames(calendar)
                withContext(Dispatchers.Main) {
                    gamesAdapter.updateGames(games)
                    updateVisibility()
                }
            } catch (e: Exception) {
                withContext((Dispatchers.Main)) {
                    Toast.makeText(
                        this@HomeActivity,
                        "Unable to connect to internet",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun updateVisibility() {
        if (gamesAdapter.itemCount == 0) {
            recyclerView.visibility = View.GONE
            emptyTextView.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            emptyTextView.visibility = View.GONE
        }
    }
}
