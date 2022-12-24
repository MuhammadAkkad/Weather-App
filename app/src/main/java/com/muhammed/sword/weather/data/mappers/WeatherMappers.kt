package com.muhammed.sword.weather.data.mappers

import com.muhammed.sword.weather.data.Constants.Companion.DAILY_DATE_FORMAT
import com.muhammed.sword.weather.data.Extensions.parseDate
import com.muhammed.sword.weather.data.model.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun WeatherDataDto.toWeatherInfo(): WeatherInfo {
    val weatherHourlyDataMap = hourly.toHourlyDataMap()
    val weatherDailyDataMap = daily.toDailyDataMap()
    val unit = hourlyUnits.temperature_2m
    val now = LocalDateTime.now()
    val currentWeatherTemp = weatherHourlyDataMap.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    val dayWeather = weatherDailyDataMap.find {
        now.dayOfMonth == it.time.dayOfMonth
    }
    var currentWeather = CurrentWeatherModel()
    dayWeather?.let {
        currentWeatherTemp?.let {
            currentWeather = CurrentWeatherModel(
                temperatureCelsius = currentWeatherTemp.temperatureCelsius,
                temperatureMax = dayWeather.temperatureMax,
                temperatureMin = dayWeather.temperatureMin,
                weatherType = dayWeather.weatherType
            )
        }
    }

    return WeatherInfo(
        currentWeather = currentWeather,
        weatherDataPerDay = weatherDailyDataMap,
        weatherDataPerHour = weatherHourlyDataMap,
        tempUnit = unit
    )
}

fun DailyDto.toDailyDataMap(): List<WeatherDataDailyModel> {
    val list = mutableListOf<WeatherDataDailyModel>()
    time.take(7).forEachIndexed { index, time ->
        list.add(
            index, WeatherDataDailyModel(
                time = time.parseDate(DAILY_DATE_FORMAT),
                temperatureMax = temperature2mMax[index],
                temperatureMin = temperature2mMin[index],
                weatherType = WeatherType.fromWMO(weatherCode[index])
            )
        )
    }
    return list
}

fun HourlyDto.toHourlyDataMap(): List<WeatherDataHourlyModel> {
    val list = mutableListOf<WeatherDataHourlyModel>()
    time.take(24).forEachIndexed { index, time ->
        list.add(
            index, WeatherDataHourlyModel(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature2m[index],
                weatherType = WeatherType.fromWMO(weathercode[index])
            )
        )
    }
    return list
}






