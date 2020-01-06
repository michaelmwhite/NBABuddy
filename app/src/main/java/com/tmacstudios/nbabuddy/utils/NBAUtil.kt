package com.tmacstudios.nbabuddy.utils

import com.tmacstudios.nbabuddy.models.api_models.Game
import java.util.*

val teamNames = mapOf(
    "ATL" to "Atlanta Hawks",
    "BKN" to "Brooklyn Nets",
    "BOS" to "Boston Celtics",
    "CHA" to "Charlotte Hornets",
    "CHI" to "Chicago Bulls",
    "CLE" to "Cleveland Cavaliers",
    "DAL" to "Dallas Mavericks",
    "DEN" to "Denver Nuggets",
    "DET" to "Detroit Pistons",
    "GSW" to "Golden State Warriors",
    "HOU" to "Houston Rockets",
    "IND" to "Indiana Pacers",
    "LAC" to "Los Angeles Clippers",
    "LAL" to "Los Angeles Lakers",
    "MEM" to "Memphis Grizzlies",
    "MIA" to "Miami Heat",
    "MIL" to "Milwaukee Bucks",
    "MIN" to "Minnesota Timberwolves",
    "NOP" to "New Orleans Pelicans",
    "NYK" to "New York Knicks",
    "OKC" to "Oklahoma City Thunder",
    "ORL" to "Orlando Magic",
    "PHI" to "Philadelphia 76ers",
    "PHX" to "Phoenix Suns",
    "POR" to "Portland Trail Blazers",
    "SAC" to "Sacramento Kings",
    "SAS" to "San Antonio Spurs",
    "TOR" to "Toronto Raptors",
    "UTA" to "Utah Jazz",
    "WAS" to "Washington Wizards"
)

fun getScoreOrTime(game: Game): String {
    val gameDate = parseUTC(game.startTimeUTC)
    if (Date().after(gameDate)) {
        return String.format("%s - %s", game.hTeam.score, game.vTeam.score)
    }
    return convertUTC(gameDate)
}

fun getQuarterTime(game: Game): String {
    val gameDate = parseUTC(game.startTimeUTC)
    if (Date().after(gameDate) && !game.isGameActivated) {
        return "Final"
    } else if (Date().after(gameDate)) {
        return String.format("Q%s  %s", game.period.current, game.clock)
    }
    return ""
}