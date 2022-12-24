package com.muhammed.sword.weather.data.model

import com.google.gson.annotations.SerializedName

data class HourlyDto(
    @SerializedName("time") val time: ArrayList<String> = arrayListOf(),
    @SerializedName("temperature_2m") val temperature2m: ArrayList<Double> = arrayListOf(),
    @SerializedName("weathercode") val weathercode: ArrayList<Int> = arrayListOf(),
) 