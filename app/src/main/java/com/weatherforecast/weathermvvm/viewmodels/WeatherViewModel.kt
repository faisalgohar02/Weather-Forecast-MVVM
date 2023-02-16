package com.weatherforecast.weathermvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.weatherforecast.weathermvvm.models.WeatherBaseModel
import com.weatherforecast.weathermvvm.repository.WeatherRepository

class WeatherViewModel()  : ViewModel() {
    val repository = WeatherRepository()

    fun getWeatherResponse(): LiveData<WeatherBaseModel> = repository.getForecastResponseLiveData()

    fun getWeatherForecast(lat: Double, lang: Double) = repository.getWeatherForecast(lat, lang)
}