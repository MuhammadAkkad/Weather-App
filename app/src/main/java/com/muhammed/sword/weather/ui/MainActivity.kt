package com.muhammed.sword.weather.ui

import android.Manifest
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.muhammed.sword.R
import com.muhammed.sword.weather.ui.WeatherState
import com.muhammed.sword.weather.ui.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupObservers()
    }

    override fun onStart() {
        super.onStart()
        getData()
    }

    private fun setupObservers() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.uiState.collect { uiState ->
                if (uiState.isLoading) {
                    showLoadingUI()
                } else if (uiState.error != null) {
                    showError(uiState)
                } else if (uiState.data != null) {
                    setupUI(uiState)
                }
            }
        }
    }

    private fun setupUI(weatherState: WeatherState) {
        showOfflineUI(weatherState.isOffline)
        val data = weatherState.data
    }

    private fun showError(error: WeatherState) {

    }

    private fun showOfflineUI(offline: Boolean) {

    }

    private fun showLoadingUI() {

    }

    private fun getData() {
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.getWeatherData()
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )
    }
}
