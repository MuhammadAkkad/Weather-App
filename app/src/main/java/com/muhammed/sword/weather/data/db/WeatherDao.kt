package com.muhammed.sword.weather.data.db

import androidx.room.*
import com.muhammed.sword.weather.data.model.WeatherData

@Dao
abstract class WeatherDao {

    @Query("SELECT * FROM weather_data")
    abstract fun getAll(): WeatherData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(data: WeatherData)

}