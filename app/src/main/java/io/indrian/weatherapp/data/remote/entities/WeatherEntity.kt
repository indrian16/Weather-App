package io.indrian.weatherapp.data.remote.entities


import com.google.gson.annotations.SerializedName

data class WeatherEntity(
    @SerializedName("description")
    val description: String = "",
    @SerializedName("icon")
    val icon: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("main")
    val main: String = ""
)