package com.muhammed.sword.weather.data.db

import androidx.room.*
import com.muhammed.sword.weather.data.model.WeatherDataDto

@Dao
abstract class WeatherDao {

    @Query("SELECT * FROM weather_data")
    abstract fun getAll(): WeatherDataDto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(data: WeatherDataDto)

}