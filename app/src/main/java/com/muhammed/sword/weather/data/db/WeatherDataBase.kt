package com.muhammed.sword.weather.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.muhammed.sword.weather.data.model.WeatherData


@Database(entities = [WeatherData::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WeatherDataBase : RoomDatabase() {

    abstract fun offlineData(): WeatherDao

}