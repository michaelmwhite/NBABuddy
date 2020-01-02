package com.tmacstudios.nbabuddy.models

import java.io.Serializable

data class TotalsTeamStats(
    val points: String,
    val fgm: String,
    val fga: String,
    val ftm: String,
    val ftp: String,
    val tpm: String,
    val tpp: String,
    val offReb: String,
    val defReb: String,
    val assists: String,
    val pFouls: String,
    val steals: String,
    val turnovers: String,
    val blocks: String,
    val plusMinus: String
) : Serializable