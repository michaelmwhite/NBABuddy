package com.tmacstudios.nbabuddy.models.api_models

import java.io.Serializable

data class Game(
    val gameId: String,
    val clock: String,
    val hTeam: Team,
    val isGameActivated: Boolean,
    val period: Period,
    val startTimeUTC: String,
    val startDateEastern: String,
    val vTeam: Team
) : Serializable