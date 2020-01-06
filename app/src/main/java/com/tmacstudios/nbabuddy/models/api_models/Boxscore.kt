package com.tmacstudios.nbabuddy.models.api_models

import java.io.Serializable

data class Boxscore(
    val basicGameData: Game,
    val stats: Stats
) : Serializable