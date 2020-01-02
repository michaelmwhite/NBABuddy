package com.tmacstudios.nbabuddy.view_models

import android.app.Activity
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
}