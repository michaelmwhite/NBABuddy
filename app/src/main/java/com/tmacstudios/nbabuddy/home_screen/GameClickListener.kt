package com.tmacstudios.nbabuddy.home_screen

import android.content.Intent
import android.view.View
import com.tmacstudios.nbabuddy.models.api_models.Game
import com.tmacstudios.nbabuddy.stats_screen.StatsActivity
class GameClickListener(val game: Game) : View.OnClickListener {
    override fun onClick(p0: View?) {
        val context = p0!!.context
        var intent = Intent(context, StatsActivity::class.java)
        intent.putExtra(StatsActivity.EXTRA_GAME, game)
        context.startActivity(intent)
    }
}