package com.muhammed.sword.weather.data.repository

import com.muhammed.sword.weather.data.api.ApiService
import com.muhammed.sword.weather.data.db.WeatherDao
import com.muhammed.sword.weather.data.model.WeatherData
import com.muhammed.sword.weather.domain.util.Resource
import com.muhammed.sword.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val dao: WeatherDao,
    private val api: ApiService
) : WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Flow<Resource<WeatherData>> {
        return flow {
            val data = api.getWeatherData(lat, long)
            if (!data.isSuccessful) {
                emit(Resource.error("Error: ${data.code()}: ${data.message()}"))
            } else {
                emit(Resource.success(data.body()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun saveDataToDb(data: WeatherData) {
        dao.insertAll(data)
    }

    override suspend fun getOfflineData(): Flow<WeatherData> {
        return flow {
            val data = dao.getAll()
            emit(data)
        }.flowOn(Dispatchers.IO)
    }

}

