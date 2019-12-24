package com.tmacstudios.nbabuddy.models

import java.io.Serializable

data class Scoreboard(
    val games: List<Game>
) : Serializable