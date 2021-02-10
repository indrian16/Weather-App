package io.indrian.weatherapp.data.repositories

import android.content.Context
import io.indrian.weatherapp.data.models.Weather
import io.indrian.weatherapp.utils.extentions.isNetworkConnected
import io.reactivex.Single

class Repository(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository,
    private val appContext: Context
) {

    fun getWeatherByCity(city: String): Single<Weather> {
        return if (appContext.isNetworkConnected()) {
            remoteRepository.getWeatherByCity(city).doAfterSuccess {
                localRepository.addOrUpdateWeather(it)
            }
        } else {
            localRepository.getLastWeather()
        }
    }

    fun getWeatherByCoordinate(lat: String, lon: String): Single<Weather> {
        return if (appContext.isNetworkConnected()) {
            remoteRepository.getWeatherByCoordinate(lat, lon).doAfterSuccess {
                localRepository.addOrUpdateWeather(it)
            }
        } else {
            localRepository.getLastWeather()
        }
    }
}