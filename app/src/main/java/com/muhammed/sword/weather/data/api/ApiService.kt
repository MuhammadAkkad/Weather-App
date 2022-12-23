package com.muhammed.sword.weather.data.api

import com.muhammed.sword.weather.data.model.WeatherData
import retrofit2.Response
import javax.inject.Inject

open class ApiService @Inject constructor(private val apiProvider: ApiProvider) {

    suspend fun getWeatherData(lat: Double, long: Double): Response<WeatherData> =
        apiProvider.getWeatherData(lat, long)

}