package com.muhammed.sword.weather.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.muhammed.sword.weather.data.model.DailyDto
import com.muhammed.sword.weather.data.model.HourlyDto
import com.muhammed.sword.weather.data.model.HourlyUnits

class Converters {

    @TypeConverter
    fun hourlyUnitsToString(hourlyUnits: HourlyUnits): String {
        return Gson().toJson(hourlyUnits).toString()
    }

    @TypeConverter
    fun stringToHourlyUnits(hourlyUnits: String): HourlyUnits {
        return Gson().fromJson(hourlyUnits, HourlyUnits::class.java)
    }

    @TypeConverter
    fun hourlyToString(hourly: HourlyDto): String {
        return Gson().toJson(hourly).toString()
    }

    @TypeConverter
    fun stringToHourly(hourly: String): HourlyDto {
        return Gson().fromJson(hourly, HourlyDto::class.java)
    }

    @TypeConverter
    fun dailyToString(dailyDto: DailyDto): String {
        return Gson().toJson(dailyDto).toString()
    }

    @TypeConverter
    fun stringToDaily(dailyDto: String): DailyDto {
        return Gson().fromJson(dailyDto, DailyDto::class.java)
    }

}