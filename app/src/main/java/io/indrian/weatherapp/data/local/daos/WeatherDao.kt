package io.indrian.weatherapp.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.indrian.weatherapp.data.models.Weather

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weathers")
    fun getWeathers(): List<Weather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weather: Weather)
}