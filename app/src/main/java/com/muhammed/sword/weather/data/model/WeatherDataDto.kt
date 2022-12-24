package com.muhammed.sword.weather.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.muhammed.sword.weather.data.db.Converters

@Entity(tableName = "weather_data")
data class WeatherDataDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @TypeConverters(Converters::class) @SerializedName("hourly_units") var hourlyUnits: HourlyUnitsDto = HourlyUnitsDto(),
    @TypeConverters(Converters::class) @SerializedName("hourly") var hourly: HourlyDto = HourlyDto(),
    @TypeConverters(Converters::class) @SerializedName("daily") var daily: DailyDto = DailyDto()
)