package io.indrian.weatherapp.utils.extensions

import java.util.*

fun Date.displayDate(): String {

    val currentDate = this
    val calendar = Calendar.getInstance().apply {
        this.time = currentDate
    }

    val month = when (calendar.get(Calendar.MONTH)) {
        0 -> "January"
        1 -> "February"
        2 -> "March"
        3 -> "April"
        4 -> "May"
        5 -> "Jun"
        6 -> "July"
        7 -> "Augustus"
        8 -> "September"
        9 -> "October"
        10 -> "November"
        11 -> "December"
        else -> "Nothing"
    }
    return "${calendar.get(Calendar.DAY_OF_MONTH)} $month, ${calendar.get(Calendar.YEAR)}"
}