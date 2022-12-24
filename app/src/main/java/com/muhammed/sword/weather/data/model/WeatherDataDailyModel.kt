package com.muhammed.sword.weather.data.model

import java.time.LocalDate

data class WeatherDataDailyModel(
    val time: LocalDate,
    val temperatureMax: Double,
    val temperatureMin: Double,
    val weatherType: WeatherType
)