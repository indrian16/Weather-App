package io.indrian.weatherapp.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.indrian.weatherapp.utils.AppConstants

@Entity(tableName = AppConstants.WEATHER_TABLE)
data class Weather(
    @PrimaryKey
    val id: Int = 0,
    var city: String = "Jakarta",

    // Weather
    val description: String = "",
    val icon: String = "",
    val main: String = "",

    // Coordinate
    val lat: Double = 0.0,
    val lon: Double = 0.0,

    // Main Data
    @ColumnInfo(name = "feels_like")
    val feelsLike: Double = 0.0,
    val humidity: Int = 0,
    val pressure: Int = 0,
    val temp: Double = 0.0,
    val tempMax: Double = 0.0,
    val tempMin: Double = 0.0,

    // Wind
    @ColumnInfo(name = "wind_deg")
    val windDeg: Int = 0,
    @ColumnInfo(name = "wind_speed")
    val windSpeed: Double = 0.0,

    // Rain
    @ColumnInfo(name = "rain_h")
    val rainH: Double = 0.0,

    // Clouds
    @ColumnInfo(name = "clouds_all")
    val cloudsAll: Int = 0
)
