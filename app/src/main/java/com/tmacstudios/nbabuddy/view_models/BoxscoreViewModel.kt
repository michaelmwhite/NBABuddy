package com.tmacstudios.nbabuddy.view_models

import android.app.Activity
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tmacstudios.nbabuddy.models.StatPair
import com.tmacstudios.nbabuddy.models.api_models.Boxscore
import com.tmacstudios.nbabuddy.models.api_models.Game
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

    fun getStatPairs(): List<StatPair> {
        val hStatTotals = boxscore.value!!.stats.hTeam.totals
        val vStatTotals = boxscore.value!!.stats.vTeam.totals
        val statsList = mutableListOf<StatPair>()
        statsList.add(StatPair("PTS", hStatTotals.points, vStatTotals.points))
        statsList.add(StatPair("FGM", hStatTotals.fgm, vStatTotals.fgm))
        statsList.add(StatPair("FGA", hStatTotals.fga, vStatTotals.fga))
        statsList.add(StatPair("FTM", hStatTotals.ftm, vStatTotals.ftm))
        statsList.add(StatPair("FT%", hStatTotals.ftp, vStatTotals.ftp))
        statsList.add(StatPair("3PT", hStatTotals.tpm, vStatTotals.tpm))
        statsList.add(StatPair("3PT%", hStatTotals.tpp, vStatTotals.tpp))
        statsList.add(StatPair("OREB", hStatTotals.offReb, vStatTotals.offReb))
        statsList.add(StatPair("DREB", hStatTotals.defReb, vStatTotals.defReb))
        statsList.add(StatPair("AST", hStatTotals.assists, vStatTotals.assists))
        statsList.add(StatPair("FOULS", hStatTotals.pFouls, vStatTotals.pFouls))
        statsList.add(StatPair("STL", hStatTotals.steals, vStatTotals.steals))
        statsList.add(StatPair("TOV", hStatTotals.turnovers, vStatTotals.turnovers))
        statsList.add(StatPair("BLK", hStatTotals.blocks, vStatTotals.blocks))
        statsList.add(StatPair("+/-", hStatTotals.plusMinus, vStatTotals.plusMinus))
        return statsList
    }
}