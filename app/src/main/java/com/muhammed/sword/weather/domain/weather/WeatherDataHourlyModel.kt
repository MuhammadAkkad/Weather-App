package com.muhammed.sword.weather.domain.weather

import com.muhammed.sword.weather.domain.weather.WeatherType
import java.time.LocalDateTime

data class WeatherDataHourlyModel(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val weatherType: WeatherType
)