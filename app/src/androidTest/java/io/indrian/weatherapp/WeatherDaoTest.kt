package io.indrian.weatherapp

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.indrian.weatherapp.data.local.AppDatabase
import io.indrian.weatherapp.data.local.daos.WeatherDao
import io.indrian.weatherapp.data.models.Weather
import io.indrian.weatherapp.di.createDB
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class WeatherDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var weatherDao: WeatherDao

    @Before
    fun initDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = createDB(context)
        weatherDao = db.weatherDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun testAddAndUpdateWeather() {
        val weather = Weather()

        // First Item
        weatherDao.insertWeather(weather)
        assertThat(weatherDao.getWeathers().first().city, equalTo("Jakarta"))

        // Second Item
        weatherDao.insertWeather(weather.apply { city = "Samarinda" })
        assertThat(weatherDao.getWeathers().first().city, equalTo("Samarinda"))
    }
}