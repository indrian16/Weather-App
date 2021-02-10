package io.indrian.weatherapp.data.remote

import io.indrian.weatherapp.data.remote.responses.WeatherResponse
import io.indrian.weatherapp.data.remote.services.WeatherService
import io.indrian.weatherapp.di.networkModule
import io.reactivex.observers.TestObserver
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import retrofit2.HttpException

class WeatherServicesTest : KoinTest {

    private val weatherService by inject<WeatherService>()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(networkModule)
    }

    @Test
    fun `test get weather by city name`() {

        val observer = TestObserver<WeatherResponse>()
        weatherService.getWeatherByCity("jakarta")
            .subscribe(observer)

        observer.assertComplete()
        observer.assertNoErrors()

        val results = observer.values()[0]

        assertEquals("Jakarta", results.name)
        assertEquals("ID", results.sys.country)

        observer.dispose()
    }

    @Test
    fun `test fail get weather by city name`() {

        val observer = TestObserver<WeatherResponse>()
        weatherService.getWeatherByCity("")
            .subscribe(observer)

        observer.assertFailure(HttpException::class.java)

        observer.dispose()
    }

    @Test
    fun `test get weather by coordinate`() {

        val observer = TestObserver<WeatherResponse>()
        weatherService.getWeatherCoordinate(lat = "-0.502106", lon =  "117.153709")
            .subscribe(observer)

        observer.assertComplete()
        observer.assertNoErrors()

        val results = observer.values()[0]

        assertEquals("Samarinda", results.name)
        assertEquals("ID", results.sys.country)

        observer.dispose()
    }

    @Test
    fun `test fail get weather by coordinate`() {

        val observer = TestObserver<WeatherResponse>()
        weatherService.getWeatherCoordinate(lat = "", lon =  "")
            .subscribe(observer)

        observer.assertFailure(HttpException::class.java)

        observer.dispose()
    }
}