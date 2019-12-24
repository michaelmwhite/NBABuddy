package com.tmacstudios.nbabuddy.models

import java.io.Serializable

data class TeamStats(
    val totals: TotalsTeamStats
) : Serializable