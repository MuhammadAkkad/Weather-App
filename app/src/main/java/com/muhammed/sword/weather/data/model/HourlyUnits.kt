package com.muhammed.sword.weather.data.model

import com.google.gson.annotations.SerializedName

data class HourlyUnits(
    @SerializedName("relativehumidity_2m") val relativeHumidity_2m: String?,
    @SerializedName("temperature_2m") val temperature_2m: String?,
    @SerializedName("time") val time: String?,
    @SerializedName("windspeed_10m") val windSpeed_10m: String?
) {
    constructor() : this(null, null, null, null)
}