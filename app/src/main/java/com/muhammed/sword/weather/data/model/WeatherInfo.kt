package com.muhammed.sword.weather.data.model

import androidx.room.Entity

data class WeatherInfo(
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?
)
