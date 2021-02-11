package io.indrian.weatherapp.ui.weather

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import io.indrian.weatherapp.R
import io.indrian.weatherapp.data.models.Weather
import io.indrian.weatherapp.ui.dialogs.LoadingDialogFragment
import io.indrian.weatherapp.utils.extensions.displayCelsius
import io.indrian.weatherapp.utils.extensions.displayDate
import io.indrian.weatherapp.utils.extensions.displayCountryName
import io.indrian.weatherapp.utils.extensions.displayWindSpeed
import kotlinx.android.synthetic.main.clouds_card_layout.*
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.android.synthetic.main.humidity_card_layout.*
import kotlinx.android.synthetic.main.weather_center_layout.*
import kotlinx.android.synthetic.main.wind_card_layout.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class WeatherFragment : Fragment() {

    private val viewModel: WeatherViewModel by viewModel()

    private val loadingDialogFragment = LoadingDialogFragment.newInstance()

    private val weatherStateObserver = Observer<WeatherState> { viewState ->
        when (viewState) {
            is WeatherState.Init -> {
                weatherDisplay(Weather())
            }
            is WeatherState.Loading -> {
                showLoading()
            }
            is WeatherState.Success -> {
                hideLoading()

                weatherDisplay(viewState.weather)
            }
            is WeatherState.Error -> {
                hideLoading()
                weatherDisplay(Weather())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchWeatherByCity("jakarta")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    private fun showLoading() {

        Timber.i("View: showLoading()")

        activity?.let { parentActivity ->
            if (!loadingDialogFragment.isAdded) {
                loadingDialogFragment.show(parentActivity.supportFragmentManager, LoadingDialogFragment.TAG)
            }
        }
    }

    private fun hideLoading() {

        Timber.i("View: hideLoading()")
        loadingDialogFragment.dismiss()
    }

    @SuppressLint("SetTextI18n")
    private fun weatherDisplay(weather: Weather) {
        tv_city.text = weather.city
        tv_country.text = weather.countryId.displayCountryName()
        tv_date.text = weather.date.displayDate()
        tv_temp.text = weather.temp.displayCelsius()
        tv_desc.text = weather.description
        tv_wind.text = weather.windSpeed.displayWindSpeed()
        tv_humidity.text = "${weather.humidity}%"
        tv_clouds.text = "${weather.cloudsAll}%"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        weatherDisplay(Weather())
        viewModel.weatherState.observe(this, weatherStateObserver)

        btn_get_weather.setOnClickListener {
            viewModel.fetchWeatherByCity("tenggarong")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = WeatherFragment()
    }
}