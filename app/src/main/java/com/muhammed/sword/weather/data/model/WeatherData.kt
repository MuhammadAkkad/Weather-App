package com.muhammed.sword.weather.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.muhammed.sword.weather.data.db.Converters

@Entity(tableName = "weather_data")
data class WeatherData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @TypeConverters(Converters::class) @SerializedName("current_weather") var currentWeather: CurrentWeather? = null,
    @SerializedName("latitude") var latitude: Double? = null,
    @SerializedName("longitude") var longitude: Double? = null,
    @SerializedName("generationtime_ms") var generationTimeMs: Double? = null,
    @SerializedName("utc_offset_seconds") var utcOffsetSeconds: Int? = null,
    @SerializedName("timezone") var timezone: String? = null,
    @SerializedName("timezone_abbreviation") var timezoneAbbreviation: String? = null,
    @SerializedName("elevation") var elevation: Int? = null,
    @TypeConverters(Converters::class) @SerializedName("hourly_units") var hourlyUnits: HourlyUnits? = null,
    @TypeConverters(Converters::class) @SerializedName("hourly") var hourly: Hourly? = null

) {
    constructor() : this(
        0, null, null, null, null,
        null, null, null, null, null, null
    )
}