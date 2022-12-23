package com.muhammed.sword.weather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammed.sword.weather.data.location.MyLocationTracker
import com.muhammed.sword.weather.data.model.WeatherData
import com.muhammed.sword.weather.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: MyLocationTracker
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherState())
    val uiState: StateFlow<WeatherState> = _uiState

    fun getWeatherData() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = WeatherState(isLoading = true)
            locationTracker.getCurrentLocation()?.let { location ->
                repository.getWeatherData(location.latitude, location.longitude)
                    .catch {
                        _uiState.value = WeatherState(error = it.message)
                        getOfflineData()
                    }
                    .collect {
                        if (it.data == null) {
                            getOfflineData()
                        } else {
                            _uiState.value = WeatherState(data = it.data, isOffline = false)
                            insertDataToDb(it.data)
                        }

                    }
            }

        }
    }

    private fun insertDataToDb(it: WeatherData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveDataToDb(it)
        }
    }

    private fun getOfflineData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getOfflineData().collect {
                _uiState.value = WeatherState(data = it, isOffline = true)
            }
        }
    }
}