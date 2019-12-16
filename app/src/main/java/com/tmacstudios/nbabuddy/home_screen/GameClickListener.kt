package com.tmacstudios.nbabuddy.home_screen

import android.view.View
import android.widget.Toast
import com.tmacstudios.nbabuddy.models.Game
import com.tmacstudios.nbabuddy.utils.loadBoxscore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameClickListener(val game: Game) : View.OnClickListener {
    override fun onClick(p0: View?) {
        GlobalScope.launch {
            try {
                val stats = loadBoxscore(game)
                withContext((Dispatchers.Main)) {
                    Toast.makeText(
                        p0!!.context,
                        "Field Goals Made: ${stats!!.hTeam.totals.fgm} vs ${stats!!.vTeam.totals.fgm}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                withContext((Dispatchers.Main)) {
                    Toast.makeText(
                        p0!!.context,
                        "Unable to get the box score for this game.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}