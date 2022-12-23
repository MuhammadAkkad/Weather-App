package com.muhammed.sword.weather.data.model

import androidx.room.Entity

data class Hourly(
    val relativehumidity_2m: List<Int>,
    val temperature_2m: List<Double>,
    val time: List<String>,
    val windspeed_10m: List<Double>
)