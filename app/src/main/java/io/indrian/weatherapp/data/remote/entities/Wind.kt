package io.indrian.weatherapp.data.remote.entities


import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("deg")
    val deg: Int = 0,
    @SerializedName("speed")
    val speed: Double = 0.0
)