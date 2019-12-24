package com.tmacstudios.nbabuddy.home_screen

import android.content.Intent
import android.view.View
import com.tmacstudios.nbabuddy.models.Game
import com.tmacstudios.nbabuddy.stats_screen.EXTRA_GAME
import com.tmacstudios.nbabuddy.stats_screen.StatsActivity

class GameClickListener(val game: Game) : View.OnClickListener {
    override fun onClick(p0: View?) {
        val context = p0!!.context
        var intent = Intent(context, StatsActivity::class.java)
        intent.putExtra(EXTRA_GAME, game)
        context.startActivity(intent)
    }
}