package com.tmacstudios.nbabuddy.utils

import com.tmacstudios.nbabuddy.models.Game
import com.tmacstudios.nbabuddy.retrofit_apis.NBAApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

val retrofit: Retrofit = Retrofit.Builder().baseUrl("http://data.nba.net/10s/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
val nbaService: NBAApi = retrofit.create(NBAApi::class.java)

suspend fun loadGames(calendar: Calendar): List<Game> {
    val response = nbaService.getScoreboard(
        String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH)),
        String.format("%02d", calendar.get(Calendar.MONTH) + 1),
        String.format("%04d", calendar.get(Calendar.YEAR))
    )
    if (response.isSuccessful) {
        return response.body()!!.games
    }
    return emptyList()
}