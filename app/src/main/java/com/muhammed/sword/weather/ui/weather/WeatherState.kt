package com.muhammed.sword.weather.ui.weather

import com.muhammed.sword.weather.domain.weather.WeatherInfo

data class WeatherState(
    val data: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val isOffline: Boolean = false,
    val error: String? = null,
)