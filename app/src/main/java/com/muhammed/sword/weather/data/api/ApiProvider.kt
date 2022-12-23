package com.muhammed.sword.weather.data.api

import com.muhammed.sword.weather.data.Constants
import com.muhammed.sword.weather.data.model.WeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface ApiProvider {
    @GET(Constants.END_POINT)
    //@GET("/error")

    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ): Response<WeatherData>
}