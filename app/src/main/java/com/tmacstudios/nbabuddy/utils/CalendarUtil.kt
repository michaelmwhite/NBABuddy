package com.tmacstudios.nbabuddy.utils

import java.text.SimpleDateFormat
import java.util.*

val pattern = "MMM dd, yyyy"
val formatter = SimpleDateFormat(pattern)

fun formatDate(calendar: Calendar): String {
    return formatter.format(calendar.time)
}