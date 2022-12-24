package com.muhammed.sword.weather.data.api

import com.muhammed.sword.weather.data.model.WeatherDataDto
import retrofit2.Response
import javax.inject.Inject

open class ApiService @Inject constructor(private val apiProvider: ApiProvider) {

    suspend fun getWeatherData(
        lat: Double,
        long: Double,
        todayDate: String,
        plusSevenDays: String
    ): Response<WeatherDataDto> =
        apiProvider.getWeatherData(lat, long, todayDate, plusSevenDays)

}