package com.muhammed.sword.weather.domain.weather

import com.muhammed.sword.weather.domain.weather.WeatherType

data class CurrentWeatherModel(
    val temperatureCelsius: Double,
    val temperatureMax: Double,
    val temperatureMin: Double,
    val weatherType: WeatherType?,
) {
    constructor() : this(0.0, 0.0, 0.0, null)
}