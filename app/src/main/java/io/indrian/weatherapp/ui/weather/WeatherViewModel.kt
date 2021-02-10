package io.indrian.weatherapp.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.indrian.weatherapp.data.repositories.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class WeatherViewModel(private val repository: Repository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _weatherState = MutableLiveData<WeatherState>()
    val weatherState: LiveData<WeatherState>
        get() = _weatherState

    init {
        _weatherState.value = WeatherState.Init
    }

    fun fetchWeatherByCity(city: String) {
        val disposable = repository.getWeatherByCity(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                Timber.d("WeatherState.Loading")
                _weatherState.value = WeatherState.Loading
            }
            .subscribe(
                { weather ->
                    Timber.d("WeatherState.Success($weather)")
                    _weatherState.value = WeatherState.Success(weather)
                },
                { error ->
                    Timber.e("WeatherState.Error(${error.message})")
                    _weatherState.value = WeatherState.Error(error.message.toString())
                }
            )

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        compositeDisposable.dispose()
        super.onCleared()
    }
}