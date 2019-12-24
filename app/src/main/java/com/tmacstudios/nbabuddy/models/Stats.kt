package com.tmacstudios.nbabuddy.models

import java.io.Serializable

data class Stats(
    val hTeam: TeamStats,
    val vTeam: TeamStats
) : Serializable