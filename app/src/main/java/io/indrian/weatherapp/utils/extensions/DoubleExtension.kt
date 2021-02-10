package io.indrian.weatherapp.utils.extensions

import kotlin.math.roundToInt

fun Double.displayCelsius(): String {
    return (this - 273.15F).roundToInt().toString()
}

fun Double.displayWindSpeed(): String {
    val value = (this * 2.23694)
    return Math.round(value).toString() + "km/h"
}