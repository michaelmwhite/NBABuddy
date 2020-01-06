package com.tmacstudios.nbabuddy.models.api_models

import java.io.Serializable

data class Team(
    val loss: String,
    val score: String,
    val triCode: String,
    val win: String
) : Serializable