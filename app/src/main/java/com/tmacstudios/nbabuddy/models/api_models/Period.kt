package com.tmacstudios.nbabuddy.models.api_models

import java.io.Serializable

data class Period(
    val current: String,
    val isHalfTime: Boolean,
    val isEndOfPeriod: Boolean
) : Serializable