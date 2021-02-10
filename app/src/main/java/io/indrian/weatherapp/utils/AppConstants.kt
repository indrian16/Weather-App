package io.indrian.weatherapp.utils

import io.indrian.weatherapp.BuildConfig

object AppConstants {

    // API
    const val BASE_URL = BuildConfig.BASE_URL
    const val APP_ID = BuildConfig.APP_ID

    // Database
    const val DB_NAME = "weather_app"
    const val DB_VER = 1
    const val WEATHER_TABLE = "weathers"
}