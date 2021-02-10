package io.indrian.weatherapp.ui.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import io.indrian.weatherapp.R
import io.indrian.weatherapp.data.models.Weather
import io.indrian.weatherapp.ui.dialogs.LoadingDialogFragment
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
            }
        }
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

    private fun weatherDisplay(weather: Weather) {
        tv_city.text = weather.city
        tv_country.text = weather.countryId
        tv_date.text = weather.date.toString()
        tv_temp.text = weather.temp.toString()
        tv_desc.text = weather.description
        tv_wind.text = weather.windSpeed.toString()
        tv_humidity.text = weather.humidity.toString()
        tv_clouds.text = weather.cloudsAll.toString()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.fetchWeatherByCity("jakarta")
        viewModel.weatherState.observe(this, weatherStateObserver)
    }

    companion object {
        @JvmStatic
        fun newInstance() = WeatherFragment()
    }
}