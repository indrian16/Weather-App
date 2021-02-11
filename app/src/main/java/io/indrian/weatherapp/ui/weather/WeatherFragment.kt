package io.indrian.weatherapp.ui.weather

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import io.indrian.weatherapp.R
import io.indrian.weatherapp.data.models.Weather
import io.indrian.weatherapp.ui.dialogs.LoadingDialogFragment
import io.indrian.weatherapp.utils.extensions.*
import kotlinx.android.synthetic.main.clouds_card_layout.*
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.android.synthetic.main.humidity_card_layout.*
import kotlinx.android.synthetic.main.weather_center_layout.*
import kotlinx.android.synthetic.main.wind_card_layout.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber


class WeatherFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

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

                toast("Something error")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchWeatherByCity("jakarta")

        activity?.let { parentActivity ->
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(parentActivity)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        weatherDisplay(Weather())
        viewModel.weatherState.observe(this, weatherStateObserver)

        btn_get_weather.setOnClickListener(::doGetWeatherByCoordinate)
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

        Glide.with(this)
                .load(weather.icon.displayWeatherCondition())
                .into(img_condition)
    }

    private fun doGetWeatherByCoordinate(view: View) {
        if (ActivityCompat.checkSelfPermission(
                        view.context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        view.context,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermission(view)
            return
        }

        fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        Timber.d("Location: $location")
                        viewModel.fetchWeatherByCoordinate(location.latitude.toString(), location.longitude.toString())
                    } else {
                        viewModel.fetchWeatherByCity("jakarta")

                        toast("Please open google maps")
                    }
                }
                .addOnFailureListener {
                    Timber.e(it.message.toString())
                }
    }

    private fun requestPermission(view: View) {

        val permissions = listOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        )

        Dexter.withContext(view.context)
                .withPermissions(permissions)
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {

                        if (p0?.areAllPermissionsGranted() == true) {
                            doGetWeatherByCoordinate(view)
                        }

                        Timber.d("onPermissionsChecked: ${p0?.grantedPermissionResponses}")
                    }

                    override fun onPermissionRationaleShouldBeShown(
                            p0: MutableList<PermissionRequest>?,
                            p1: PermissionToken?
                    ) {
                        p1?.continuePermissionRequest()
                    }
                })
                .check()
    }

    companion object {
        @JvmStatic
        fun newInstance() = WeatherFragment()
    }
}