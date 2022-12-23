package com.muhammed.sword.weather.domain.repository

import com.muhammed.sword.weather.data.model.WeatherData
import com.muhammed.sword.weather.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Flow<Resource<WeatherData>>
    suspend fun getOfflineData(): Flow<WeatherData>
    fun saveDataToDb(data: WeatherData)
}