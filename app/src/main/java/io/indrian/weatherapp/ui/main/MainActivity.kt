package io.indrian.weatherapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.indrian.weatherapp.R
import io.indrian.weatherapp.ui.weather.WeatherFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_host, WeatherFragment.newInstance())
                .commit()
        }
    }
}