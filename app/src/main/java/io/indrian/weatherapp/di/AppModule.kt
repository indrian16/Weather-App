package io.indrian.weatherapp.di

import io.indrian.weatherapp.ui.weather.WeatherViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { WeatherViewModel(get()) }
}