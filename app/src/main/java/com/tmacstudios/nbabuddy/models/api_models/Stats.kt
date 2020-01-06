package com.tmacstudios.nbabuddy.models.api_models

import java.io.Serializable

data class Stats(
    val hTeam: TeamStats,
    val vTeam: TeamStats
) : Serializable