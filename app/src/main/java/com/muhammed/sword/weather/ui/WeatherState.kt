package com.muhammed.sword.weather.ui

import com.muhammed.sword.weather.data.model.WeatherInfo

data class WeatherState(
    val data: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val isOffline: Boolean = false,
    val error: String? = null,
)