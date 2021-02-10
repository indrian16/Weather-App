package io.indrian.weatherapp.data.remote.entities


import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("1h")
    val h: Double = 0.0
)