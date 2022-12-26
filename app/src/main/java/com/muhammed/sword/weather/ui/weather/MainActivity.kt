package com.muhammed.sword.weather.ui.weather

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.muhammed.sword.R
import com.muhammed.sword.databinding.ActivityMainBinding
import com.muhammed.sword.weather.ui.adapter.DailyAdapter
import com.muhammed.sword.weather.ui.adapter.HourlyAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModels()

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    private lateinit var binding: ActivityMainBinding

    private lateinit var rvHours: HourlyAdapter

    private lateinit var rvDaily: DailyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSwipeToRefresh()
        setupObservers()
        setUpRecyclerViews()
        registerForActivityResult()
        getOfflineData()
    }

    override fun onStart() {
        super.onStart()
        checkForPermission()
    }

    private fun setupObservers() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.uiState.collect { uiState ->
                binding.swipeToRefresh.isRefreshing = uiState.isLoading
                if (uiState.error != null) {
                    showError()
                } else if (uiState.data != null) {
                    setupUI(uiState)
                }
            }
        }
    }

    private fun setUpRecyclerViews() {
        rvHours = HourlyAdapter()
        rvDaily = DailyAdapter()
        binding.hourlyRv.adapter = rvHours
        binding.dailyRv.adapter = rvDaily
        binding.dailyRv.layoutManager = LinearLayoutManager(this)
        binding.hourlyRv.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL, false
        )
    }

    private fun setupSwipeToRefresh() {
        binding.swipeToRefresh.setOnRefreshListener {
            getData()
        }
    }

    private fun setupUI(weatherState: WeatherState) {
        weatherState.data?.let { weatherInfo ->
            binding.data = weatherInfo.currentWeather // current data
            rvHours.addData(weatherInfo.weatherDataPerHour)
            rvDaily.addData(weatherInfo.weatherDataPerDay)
        }
    }

    private fun showError() {
        Snackbar.make(binding.root, getString(R.string.error), Snackbar.LENGTH_LONG).show()
    }

    private fun getData() {
        viewModel.getWeatherData()
    }

    private fun getOfflineData() {
        // if available could save user couple of second until online data is ready
        viewModel.getOfflineData()
    }

    private fun registerForActivityResult() {
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { map ->
            if (!map.all { it.value }) { // if not granted show popup
                showPopUp()
            } else {
                getData()
            }
        }
    }

    private fun checkForPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_DENIED
        ) showPopUp()
        else getData()
    }

    private fun showPopUp() {
        val builder: MaterialAlertDialogBuilder = MaterialAlertDialogBuilder(
            this, R.style.CustomMaterialDialog
        ).setTitle(getString(R.string.permission_title))
            .setMessage(getString(R.string.permission_message))
            .setPositiveButton(android.R.string.ok) { _, _ ->
                run {
                    askForPermission()
                }
            }.setNegativeButton(android.R.string.cancel) { _, _ ->
                run {
                    finish()
                }
            }
        builder.show()
    }

    private fun askForPermission() {
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )
    }

}
