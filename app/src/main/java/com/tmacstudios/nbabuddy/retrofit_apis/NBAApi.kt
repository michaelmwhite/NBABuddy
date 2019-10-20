package com.tmacstudios.nbabuddy.retrofit_apis

import com.tmacstudios.nbabuddy.models.Game
import com.tmacstudios.nbabuddy.models.ScoreboardResponse
import retrofit2.Call
import retrofit2.http.GET

interface NBAApi {
    @GET("prod/v1/20191016/scoreboard.json")
    fun listGames(): Call<ScoreboardResponse>
}