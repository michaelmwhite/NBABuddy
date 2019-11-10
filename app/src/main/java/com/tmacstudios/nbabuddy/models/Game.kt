package com.tmacstudios.nbabuddy.models

class Game(
    val clock: String,
    val hTeam: Team,
    val isGameActivated: Boolean,
    val period: Period,
    val startTimeUTC: String,
    val vTeam: Team
)