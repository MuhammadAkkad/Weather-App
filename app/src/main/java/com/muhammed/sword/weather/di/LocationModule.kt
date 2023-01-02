package com.muhammed.sword.weather.di

import com.muhammed.sword.weather.data.location.LocationTrackerImp
import com.muhammed.sword.weather.domain.location.LocationTracker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton


@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {

    @Binds
    @Singleton
    abstract fun bindWeatherLocationTracker(
        weatherRepositoryImpl: LocationTrackerImp
    ): LocationTracker

}