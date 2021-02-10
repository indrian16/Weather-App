package io.indrian.weatherapp.data.remote.services

import io.indrian.weatherapp.data.remote.responses.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("/weather")
    fun getWeatherByCity(
        @Query("q") city: String,
        @Query("appid") appId: String
    ): Single<WeatherResponse>

    @GET("/weather")
    fun getWeatherCoordinate(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appId: String
    ): Single<WeatherResponse>
}