package com.tmacstudios.nbabuddy.models

import java.io.Serializable

data class TotalsTeamStats(
    val points: String,
    val fgm: String,
    val fga: String
) : Serializable