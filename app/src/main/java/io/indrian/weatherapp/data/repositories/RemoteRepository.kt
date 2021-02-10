package io.indrian.weatherapp.data.repositories

import io.indrian.weatherapp.data.mappers.WeatherMapper
import io.indrian.weatherapp.data.models.Weather
import io.indrian.weatherapp.data.remote.services.WeatherService
import io.reactivex.Single

class RemoteRepository(
    private val weatherService: WeatherService,
    private val weatherMapper: WeatherMapper
) {

    fun getWeatherByCity(city: String): Single<Weather> {
        return weatherService.getWeatherByCity(city)
            .map {
                weatherMapper.toModel(it)
            }
    }

    fun getWeatherByCoordinate(lat: String, lon: String): Single<Weather> {
        return weatherService.getWeatherCoordinate(lat, lon)
            .map {
                weatherMapper.toModel(it)
            }
    }
}