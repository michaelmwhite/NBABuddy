package com.tmacstudios.nbabuddy.utils

import java.text.SimpleDateFormat
import java.util.*

const val datePattern = "MMM dd, yyyy"
const val utcPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
const val timePattern = "h:mm a"
val dateFormatter = SimpleDateFormat(datePattern)
val utcFormatter = SimpleDateFormat(utcPattern)
val timeFormatter = SimpleDateFormat(timePattern)

fun formatDate(calendar: Calendar): String {
    return dateFormatter.format(calendar.time)
}

fun parseUTC(timeUTC: String): Date {
    utcFormatter.timeZone = TimeZone.getTimeZone("UTC")
    return utcFormatter.parse(timeUTC)
}

fun convertUTC(date: Date): String{
    timeFormatter.timeZone = TimeZone.getDefault()
    return timeFormatter.format(date)
}