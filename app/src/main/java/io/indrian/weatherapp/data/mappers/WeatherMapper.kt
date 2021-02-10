package io.indrian.weatherapp.data.mappers

import io.indrian.weatherapp.data.models.Weather
import io.indrian.weatherapp.data.remote.responses.WeatherResponse
import timber.log.Timber

class WeatherMapper : BaseMapper<WeatherResponse, Weather> {
    override fun toModel(entity: WeatherResponse): Weather {
        Timber.d("Mapping: $entity")
        val weather = entity.weatherEntity[0]
        val coordinate = entity.coord
        val main = entity.main
        val wind = entity.wind
        val rain = entity.rain
        val clouds = entity.clouds
        return Weather(
            city = entity.name,
            description = weather.description,
            icon = weather.icon,
            main = weather.main,
            lat = coordinate.lat,
            lon = coordinate.lon,
            feelsLike = main.feelsLike,
            humidity = main.humidity,
            temp = main.temp,
            tempMax = main.tempMax,
            tempMin = main.tempMin,
            windDeg = wind.deg,
            windSpeed = wind.speed,
            rainH = rain.h,
            cloudsAll = clouds.all
        )
    }
}