package com.tmacstudios.nbabuddy.home_screen

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tmacstudios.nbabuddy.R
import com.tmacstudios.nbabuddy.retrofit_apis.NBAApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder().baseUrl("http://data.nba.net/10s/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val nbaService = retrofit.create(NBAApi::class.java)

        Thread {
            val response = nbaService.listGames().execute()
            if (response.isSuccessful) {
                response.body()?.games?.map {
                    Log.d("HomeActivity.kt", it.gameId)
                }
            }
        }.start()
    }
}
