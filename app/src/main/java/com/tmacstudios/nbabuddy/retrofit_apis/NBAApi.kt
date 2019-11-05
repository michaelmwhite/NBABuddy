package com.tmacstudios.nbabuddy.retrofit_apis

import com.tmacstudios.nbabuddy.models.Scoreboard
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NBAApi {
    @GET("prod/v1/{year}{month}{day}/scoreboard.json")
    suspend fun getScoreboard(
        @Path("day") day: Int,
        @Path("month") month: Int,
        @Path("year") year: Int
    ): Response<Scoreboard>
}