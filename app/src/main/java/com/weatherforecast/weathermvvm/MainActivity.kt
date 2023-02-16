package com.weatherforecast.weathermvvm

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.weatherforecast.weathermvvm.adapters.WeatherAdapter
import com.weatherforecast.weathermvvm.commondata.WeatherCommon
import com.weatherforecast.weathermvvm.commondata.WeatherCommon.isInternetConnected
import com.weatherforecast.weathermvvm.databinding.ActivityMainBinding
import com.weatherforecast.weathermvvm.viewmodels.WeatherViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var mAdapter: WeatherAdapter
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.run {
            layoutManager = GridLayoutManager(this@MainActivity,2)
            mAdapter = WeatherAdapter(this@MainActivity)
            adapter = mAdapter
        }
        weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        if (isInternetConnected(this)) {
            weatherViewModel.getWeatherForecast(WeatherCommon.latitude, WeatherCommon.longitude)
            weatherViewModel.getWeatherResponse().observe(this@MainActivity) {
                mAdapter.updateList(it)
            }
        } else {
            Toast.makeText(this, "First Connect With Internet.", Toast.LENGTH_SHORT).show()
        }
    }
}