package com.muhammed.sword.weather.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.muhammed.sword.weather.data.model.CurrentWeather
import com.muhammed.sword.weather.data.model.Hourly
import com.muhammed.sword.weather.data.model.HourlyUnits
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Converters {

    @TypeConverter
    fun dateToString(date: LocalDateTime): String {
        return date.toString()
    }

    @TypeConverter
    fun stringToDate(stringDate: String): LocalDateTime {
        return LocalDateTime.parse(stringDate, DateTimeFormatter.ISO_DATE_TIME);
    }

    @TypeConverter
    fun currentWeatherToString(currentWeather: CurrentWeather): String {
        return Gson().toJson(currentWeather).toString()
    }

    @TypeConverter
    fun stringToCurrentWeather(currentWeather: String): CurrentWeather {
        return Gson().fromJson(currentWeather, CurrentWeather::class.java)
    }

    @TypeConverter
    fun hourlyUnitsToString(hourlyUnits: HourlyUnits): String {
        return Gson().toJson(hourlyUnits).toString()
    }

    @TypeConverter
    fun stringToHourlyUnits(hourlyUnits: String): HourlyUnits {
        return Gson().fromJson(hourlyUnits, HourlyUnits::class.java)
    }

    @TypeConverter
    fun hourlyToString(hourly: Hourly): String {
        return Gson().toJson(hourly).toString()
    }

    @TypeConverter
    fun stringToHourly(hourly: String): Hourly {
        return Gson().fromJson(hourly, Hourly::class.java)
    }


}