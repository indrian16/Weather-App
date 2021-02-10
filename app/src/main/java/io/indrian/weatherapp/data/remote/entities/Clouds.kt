package io.indrian.weatherapp.data.remote.entities


import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    val all: Int = 0
)