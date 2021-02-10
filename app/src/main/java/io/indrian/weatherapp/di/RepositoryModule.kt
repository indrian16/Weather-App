package io.indrian.weatherapp.di

import io.indrian.weatherapp.data.mappers.WeatherMapper
import io.indrian.weatherapp.data.repositories.LocalRepository
import io.indrian.weatherapp.data.repositories.RemoteRepository
import io.indrian.weatherapp.data.repositories.Repository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    factory { LocalRepository(get()) }
    factory { RemoteRepository(get(), WeatherMapper()) }

    single { Repository(get(), get(), androidContext()) }
}