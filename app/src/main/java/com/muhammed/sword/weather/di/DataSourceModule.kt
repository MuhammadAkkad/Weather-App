package com.muhammed.sword.weather.di

import android.app.Application
import android.content.Context
import android.location.Location
import androidx.room.Room
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.muhammed.sword.weather.data.Constants
import com.muhammed.sword.weather.data.api.ApiProvider
import com.muhammed.sword.weather.data.db.WeatherDao
import com.muhammed.sword.weather.data.db.WeatherDataBase
import com.muhammed.sword.weather.data.location.MyLocationTracker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun providesApiService(): ApiProvider =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiProvider::class.java)

    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): WeatherDataBase {
        return Room.databaseBuilder(
            appContext,
            WeatherDataBase::class.java,
            Constants.DatabaseName
        ).build()
    }

    @Provides
    fun provideOfflineData(database: WeatherDataBase): WeatherDao {
        return database.offlineData()
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application) : FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

}