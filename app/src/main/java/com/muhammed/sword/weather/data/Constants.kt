package com.muhammed.sword.weather.data

class Constants {
    companion object {
        const val DatabaseName = "database_name"
        const val BASE_URL = "https://api.open-meteo.com/"
        const val END_POINT =
            "/v1/forecast?latitude=52.52&longitude=13.41&current_weather=true&hourly=temperature_2m,relativehumidity_2m,windspeed_10m"
    }
}