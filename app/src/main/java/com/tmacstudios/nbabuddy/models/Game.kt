package com.tmacstudios.nbabuddy.models

class Game(
    val gameId: String,
    val clock: String,
    val hTeam: Team,
    val isGameActivated: Boolean,
    val period: Period,
    val startTimeUTC: String,
    val startDateEastern: String,
    val vTeam: Team
)