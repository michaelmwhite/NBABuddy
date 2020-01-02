package com.tmacstudios.nbabuddy.view_models

import android.app.Activity
import android.text.Html
import android.text.Spanned
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tmacstudios.nbabuddy.models.Boxscore
import com.tmacstudios.nbabuddy.models.Game
import com.tmacstudios.nbabuddy.utils.loadBoxscore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BoxscoreViewModel : ViewModel() {
    val boxscore: MutableLiveData<Boxscore> by lazy {
        MutableLiveData<Boxscore>()
    }

    fun retrieveBoxscore(game: Game, activity: Activity) {
        GlobalScope.launch {
            try {
                val boxscoreData = loadBoxscore(game)!!
                withContext((Dispatchers.Main)) {
                    boxscore.value = boxscoreData
                }
            } catch (e: Exception) {
                withContext((Dispatchers.Main)) {
                    Toast.makeText(
                        activity,
                        "Unable to retrieve this boxscore.",
                        Toast.LENGTH_SHORT
                    ).show()
                    activity.finish()
                }
            }
        }
    }

    fun getStatsList(): List<Spanned> {
        val hStatTotals = boxscore.value!!.stats.hTeam.totals
        val vStatTotals = boxscore.value!!.stats.vTeam.totals
        val statsList = mutableListOf<Spanned>()
        statsList.add(formatStat("Points", hStatTotals.points, vStatTotals.points))
        statsList.add(formatStat("Field Goals Made", hStatTotals.fgm, vStatTotals.fgm))
        statsList.add(formatStat("Field Goal Attempts", hStatTotals.fga, vStatTotals.fga))
        statsList.add(formatStat("Free Throws Made", hStatTotals.ftm, vStatTotals.ftm))
        statsList.add(formatStat("Free Throw %", hStatTotals.ftp, vStatTotals.ftp))
        statsList.add(formatStat("Three Pointers Made", hStatTotals.tpm, vStatTotals.tpm))
        statsList.add(formatStat("Three Point %", hStatTotals.tpp, vStatTotals.tpp))
        statsList.add(formatStat("Offensive Rebounds", hStatTotals.offReb, vStatTotals.offReb))
        statsList.add(formatStat("Defensive Rebounds", hStatTotals.defReb, vStatTotals.defReb))
        statsList.add(formatStat("Assists", hStatTotals.assists, vStatTotals.assists))
        statsList.add(formatStat("Fouls", hStatTotals.pFouls, vStatTotals.pFouls))
        statsList.add(formatStat("Steals", hStatTotals.steals, vStatTotals.steals))
        statsList.add(formatStat("Turnovers", hStatTotals.turnovers, vStatTotals.turnovers))
        statsList.add(formatStat("Blocks", hStatTotals.blocks, vStatTotals.blocks))
        statsList.add(formatStat("Plus Minus", hStatTotals.plusMinus, vStatTotals.plusMinus))
        return statsList
    }

    private fun formatStat(name: String, hStat: String, vStat: String): Spanned {
        return Html.fromHtml("$hStat <b>$name</b> $vStat")
    }
}