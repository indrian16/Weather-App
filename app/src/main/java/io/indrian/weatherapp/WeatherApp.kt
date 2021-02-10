package io.indrian.weatherapp

import android.app.Application
import io.indrian.weatherapp.di.localModule
import io.indrian.weatherapp.di.remoteModule
import io.indrian.weatherapp.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidLogger()
            androidContext(this@WeatherApp)
            modules(
                remoteModule,
                localModule,
                repositoryModule
            )
        }
    }
}