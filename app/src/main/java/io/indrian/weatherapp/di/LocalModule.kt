package io.indrian.weatherapp.di

import android.content.Context
import androidx.room.Room
import io.indrian.weatherapp.data.local.AppDatabase
import io.indrian.weatherapp.utils.AppConstants
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localModule = module {

    single {
        createDB(androidApplication())
    }

    factory { get<AppDatabase>().weatherDao() }
}

fun createDB(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        AppConstants.DB_NAME
    ).build()
}