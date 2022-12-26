package com.muhammed.sword.weather.domain.weather

import java.time.LocalDateTime

data class WeatherDataHourlyModel(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val weatherType: WeatherType
)