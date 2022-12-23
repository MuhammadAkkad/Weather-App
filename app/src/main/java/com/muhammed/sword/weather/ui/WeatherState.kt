package com.muhammed.sword.weather.ui

import com.muhammed.sword.weather.data.model.WeatherData

data class WeatherState(
    val data: WeatherData? = null,
    val isLoading: Boolean = false,
    val isOffline: Boolean = false,
    val error: String? = null,
)