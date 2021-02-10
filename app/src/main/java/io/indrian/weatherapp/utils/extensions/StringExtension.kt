package io.indrian.weatherapp.utils.extensions

fun String.displayCountryName(): String {
    return when (this) {
        "US" -> "United States"
        "ID" -> "Indonesia"
        else -> "World"
    }
}