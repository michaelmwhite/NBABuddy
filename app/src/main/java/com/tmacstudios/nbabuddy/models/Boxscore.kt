package com.tmacstudios.nbabuddy.models

import java.io.Serializable

data class Boxscore(
    val basicGameData: Game,
    val stats: Stats
) : Serializable