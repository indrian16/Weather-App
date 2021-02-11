package io.indrian.weatherapp.utils.extensions

import io.indrian.weatherapp.R

fun String.displayCountryName(): String {
    return when (this) {
        "US" -> "United States"
        "ID" -> "Indonesia"
        else -> "World"
    }
}

fun String.displayWeatherCondition(): Int {
    return when (this) {
        "01d" -> R.drawable.sun
        "01n" -> R.drawable.sunset

        "02d" -> R.drawable.few_clouds_day
        "02n" -> R.drawable.few_clouds_night

        "03d" -> R.drawable.cloud
        "03n" -> R.drawable.cloud

        "04d" -> R.drawable.cloud
        "04n" -> R.drawable.cloud

        "09d" -> R.drawable.cloud
        "09n" -> R.drawable.cloud

        else -> R.drawable.few_clouds_day
    }
}