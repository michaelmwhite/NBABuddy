package com.tmacstudios.nbabuddy.utils

import com.tmacstudios.nbabuddy.models.Game
import com.tmacstudios.nbabuddy.models.Stats
import com.tmacstudios.nbabuddy.retrofit_apis.NBAApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

// Note: http://data.nba.net/10s/prod/v1/today.json  has the format for all endpoints
val retrofit: Retrofit = Retrofit.Builder().baseUrl("http://data.nba.net/10s/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
val nbaService: NBAApi = retrofit.create(NBAApi::class.java)

suspend fun loadGames(calendar: Calendar): List<Game>? {
    val response = nbaService.getScoreboard(
        String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH)),
        String.format("%02d", calendar.get(Calendar.MONTH) + 1),
        String.format("%04d", calendar.get(Calendar.YEAR))
    )
    if (response.isSuccessful) {
        return response.body()!!.games
    }
    return null
}

suspend fun loadBoxscore(game: Game): Stats? {
    val response = nbaService.getBoxscore(
        game.startDateEastern,
        game.gameId
    )
    if (response.isSuccessful) {
        return response.body()!!.stats
    }
    return null
}