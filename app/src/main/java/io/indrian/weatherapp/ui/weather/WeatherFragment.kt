package io.indrian.weatherapp.ui.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import io.indrian.weatherapp.R
import io.indrian.weatherapp.ui.dialogs.LoadingDialogFragment
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class WeatherFragment : Fragment() {

    private val viewModel: WeatherViewModel by viewModel()

    private val loadingDialogFragment = LoadingDialogFragment.newInstance()

    private val weatherStateObserver = Observer<WeatherState> { viewState ->
        when (viewState) {
            is WeatherState.Init -> {}
            is WeatherState.Loading -> {
                showLoading()
            }
            is WeatherState.Success -> {
                hideLoading()
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