package io.indrian.weatherapp.ui.weather

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import io.indrian.weatherapp.utils.extensions.displayCelsius
import io.indrian.weatherapp.utils.extensions.displayCountryName
import io.indrian.weatherapp.utils.extensions.displayDate
import io.indrian.weatherapp.utils.extensions.displayWindSpeed

@SuppressLint("SetTextI18n")
@BindingAdapter("weatherCity")
fun setWeatherCityName(view: TextView, weatherState: WeatherState) {

    if (weatherState is WeatherState.Init) {
        view.text = "Jakarta"
    }

    if (weatherState is WeatherState.Success) {
        view.text = weatherState.weather.city
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("weatherCountry")
fun setWeatherCountry(view: TextView, weatherState: WeatherState) {
    if (weatherState is WeatherState.Init) {
        view.text = "Indonesia"
    }

    if (weatherState is WeatherState.Success) {
        view.text = weatherState.weather.countryId.displayCountryName()
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("weatherDate")
fun setWeatherDate(view: TextView, weatherState: WeatherState) {
    if (weatherState is WeatherState.Init) {
        view.text = "Now"
    }

    if (weatherState is WeatherState.Success) {
        view.text = weatherState.weather.date.displayDate()
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("weatherTemp")
fun setWeatherTemp(view: TextView, weatherState: WeatherState) {
    if (weatherState is WeatherState.Init) {
        view.text = "20"
    }

    if (weatherState is WeatherState.Success) {
        view.text = weatherState.weather.temp.displayCelsius()
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("weatherWindSpeed")
fun setWeatherWindSpeed(view: TextView, weatherState: WeatherState) {
    if (weatherState is WeatherState.Init) {
        view.text = "2km/h"
    }

    if (weatherState is WeatherState.Success) {
        view.text = weatherState.weather.windSpeed.displayWindSpeed()
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("weatherHumidity")
fun setWeatherHumidity(view: TextView, weatherState: WeatherState) {
    if (weatherState is WeatherState.Init) {
        view.text = "50%"
    }

    if (weatherState is WeatherState.Success) {
        view.text = "${weatherState.weather.humidity}%"
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("weatherClouds")
fun setWeatherClouds(view: TextView, weatherState: WeatherState) {
    if (weatherState is WeatherState.Init) {
        view.text = "40%"
    }

    if (weatherState is WeatherState.Success) {
        view.text = "${weatherState.weather.cloudsAll}%"
    }
}