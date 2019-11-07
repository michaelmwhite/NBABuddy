package com.tmacstudios.nbabuddy.retrofit_apis

import com.tmacstudios.nbabuddy.models.Scoreboard
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// Value inputs must be strings as values must occupy a fixed number of digits
interface NBAApi {
    @GET("prod/v1/{year}{month}{day}/scoreboard.json")
    suspend fun getScoreboard(
        @Path("day") day: String,
        @Path("month") month: String,
        @Path("year") year: String
    ): Response<Scoreboard>
}