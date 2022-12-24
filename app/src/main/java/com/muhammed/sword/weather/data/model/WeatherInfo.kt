package com.muhammed.sword.weather.data.model

data class WeatherInfo(
    val weatherDataPerDay: List<WeatherDataDailyModel> ,
    val weatherDataPerHour: List<WeatherDataHourlyModel>,
    val currentWeather: CurrentWeatherModel?,
    val tempUnit: String?,
)
