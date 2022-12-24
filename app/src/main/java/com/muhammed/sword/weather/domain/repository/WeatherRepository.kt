package com.muhammed.sword.weather.domain.repository

import com.muhammed.sword.weather.data.model.WeatherDataDto
import com.muhammed.sword.weather.domain.weather.WeatherInfo
import com.muhammed.sword.weather.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double, todayDate: String, plusSevenDays: String): Flow<Resource<WeatherInfo>>
    suspend fun getOfflineData(): Flow<WeatherInfo?>
    fun saveDataToDb(data: WeatherDataDto?)
}