package com.muhammed.sword.weather.domain.weather

data class WeatherInfo(
    val weatherDataPerDay: List<WeatherDataDailyModel>,
    val weatherDataPerHour: List<WeatherDataHourlyModel>,
    val currentWeather: CurrentWeatherModel?,
    val tempUnit: String?,
)
