package com.muhammed.sword.weather.domain.weather

import java.time.LocalDate

data class WeatherDataDailyModel(
    val time: LocalDate,
    val temperatureMax: Double,
    val temperatureMin: Double,
    val weatherType: WeatherType
)