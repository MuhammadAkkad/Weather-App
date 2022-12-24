package com.muhammed.sword.weather.ui.weather

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
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
                if (uiState.error != null) {
                    showError(uiState)
                } else if (uiState.data != null) {
                    setupUI(uiState)
                    binding.swipeToRefresh.isEnabled = true
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
        // disable by default. enabled when data is ready on screen.
        binding.swipeToRefresh.isEnabled = false
        binding.swipeToRefresh.setOnRefreshListener {
            getData()
            binding.swipeToRefresh.isRefreshing = false
        }
    }

    private fun setupUI(weatherState: WeatherState) {
        binding.connectingLayout.visibility =
            if (weatherState.isLoading) View.VISIBLE else View.GONE
        weatherState.data?.let { weatherInfo ->
            binding.data = weatherInfo.currentWeather
            weatherState.data.let {
                rvHours.addData(it.weatherDataPerHour)
                rvDaily.addData(it.weatherDataPerDay)
            }
        }
    }

    private fun showError(data: WeatherState) {
        Snackbar.make(binding.swipeToRefresh, data.error.toString(), Snackbar.LENGTH_SHORT).show()
    }

    private fun getData() {
        binding.swipeToRefresh.isEnabled = false
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
            if (!map.all { it.value }) {
                showPopUp()
            } else {
                getData()
            }
        }
    }

    private fun checkForPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_DENIED
        ) showPopUp()
        else getData()
    }

    private fun showPopUp() {
        val builder: MaterialAlertDialogBuilder =
            MaterialAlertDialogBuilder(
                this,
                R.style.CustomMaterialDialog
            ).setTitle(getString(R.string.permission_title))
                .setMessage(getString(R.string.permission_message))
                .setIcon(android.R.drawable.ic_menu_mylocation)
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
