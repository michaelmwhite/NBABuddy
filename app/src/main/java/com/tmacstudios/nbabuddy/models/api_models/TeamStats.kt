package com.tmacstudios.nbabuddy.models.api_models

import java.io.Serializable

data class TeamStats(
    val totals: TotalsTeamStats
) : Serializable