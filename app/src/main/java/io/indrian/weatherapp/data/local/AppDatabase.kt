package io.indrian.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.indrian.weatherapp.data.local.converters.Converters
import io.indrian.weatherapp.data.local.daos.WeatherDao
import io.indrian.weatherapp.data.models.Weather
import io.indrian.weatherapp.utils.AppConstants

@Database(
    entities = [Weather::class],
    version = AppConstants.DB_VER
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}