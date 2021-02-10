package io.indrian.weatherapp.data.remote.services

import io.indrian.weatherapp.data.remote.responses.WeatherResponse
import io.indrian.weatherapp.utils.AppConstants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/weather")
    fun getWeatherByCity(
        @Query("q") city: String,
        @Query("appid") appId: String = AppConstants.APP_ID
    ): Single<WeatherResponse>

    @GET("data/2.5/weather")
    fun getWeatherCoordinate(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appId: String = AppConstants.APP_ID
    ): Single<WeatherResponse>
}