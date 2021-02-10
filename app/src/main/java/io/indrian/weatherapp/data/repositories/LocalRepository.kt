package io.indrian.weatherapp.data.repositories

import io.indrian.weatherapp.data.local.daos.WeatherDao
import io.indrian.weatherapp.data.models.Weather
import io.reactivex.Single
import timber.log.Timber
import java.lang.Exception

class LocalRepository(private val weatherDao: WeatherDao) {

    fun addOrUpdateWeather(weather: Weather) {
        Timber.d("update: $weather")
        weatherDao.insertWeather(weather)
    }

    fun getLastWeather(): Single<Weather> {
        return Single.create { emitter ->
            try {
                val lastWeather = weatherDao.getWeathers().first()
                Timber.d("Success getLastWeather(:$lastWeather)")
                emitter.onSuccess(lastWeather)
            } catch (e: Exception) {
                Timber.e("Fail getLastWeather")
                emitter.onError(e)
            }
        }
    }
}