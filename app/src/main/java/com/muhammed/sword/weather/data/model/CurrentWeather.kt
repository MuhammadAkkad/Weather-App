package com.muhammed.sword.weather.data.model

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    @SerializedName("temperature") val temperature: Double,
    @SerializedName("time") val time: String,
    @SerializedName("weathercode") val weatherCode: Int,
    @SerializedName("winddirection") val windDirection: Double,
    @SerializedName("windspeed") val windSpeed: Double
)