package com.muhammed.sword.weather.data.model

import java.time.LocalDateTime

data class WeatherDataHourlyModel(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val weatherType: WeatherType
) {
}