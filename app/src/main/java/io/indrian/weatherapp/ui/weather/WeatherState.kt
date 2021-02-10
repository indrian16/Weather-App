package io.indrian.weatherapp.ui.weather

import io.indrian.weatherapp.data.models.Weather

sealed class WeatherState {
    object Init : WeatherState()
    object Loading : WeatherState()
    data class Success(val weather: Weather) : WeatherState()
    data class Error(val message: String) : WeatherState()
}