package com.muhammed.sword.weather.data

class Constants {
    companion object {
        const val DatabaseName = "database_weather"
        const val DAILY_DATE_FORMAT = "yyyy-MM-dd"
        const val BASE_URL = "https://api.open-meteo.com/"
        const val END_POINT = "/v1/forecast?&timezone=auto" +
                "&hourly=temperature_2m,weathercode" +
                "&daily=temperature_2m_max,temperature_2m_min,weathercode"
    }
}