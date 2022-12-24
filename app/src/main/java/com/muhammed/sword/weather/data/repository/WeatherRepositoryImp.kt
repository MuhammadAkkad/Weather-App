package com.muhammed.sword.weather.data.repository

import com.muhammed.sword.weather.data.api.ApiService
import com.muhammed.sword.weather.data.db.WeatherDao
import com.muhammed.sword.weather.data.mappers.toWeatherInfo
import com.muhammed.sword.weather.data.model.WeatherDataDto
import com.muhammed.sword.weather.data.model.WeatherInfo
import com.muhammed.sword.weather.domain.repository.WeatherRepository
import com.muhammed.sword.weather.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val dao: WeatherDao,
    private val api: ApiService
) : WeatherRepository {

    override suspend fun getWeatherData(
        lat: Double,
        long: Double,
        todayDate: String,
        plusSevenDays: String
    ): Flow<Resource<WeatherInfo>> {
        return flow {
            val data = api.getWeatherData(lat, long, todayDate, plusSevenDays)
            if (!data.isSuccessful) {
                emit(Resource.error("Error: ${data.code()}: ${data.message()}"))
            } else {
                emit(Resource.success(data.body()?.toWeatherInfo()))
                saveDataToDb(data.body())
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun saveDataToDb(data: WeatherDataDto?) {
        data?.let {
            dao.insertAll(it)
        }
    }

    override suspend fun getOfflineData(): Flow<WeatherInfo?> {
        return flow {
            val data = dao.getAll()?.toWeatherInfo()
            data?.let {
                emit(it)
            }
        }.flowOn(Dispatchers.IO)
    }

}

