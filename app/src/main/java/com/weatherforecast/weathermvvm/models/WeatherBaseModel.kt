package com.weatherforecast.weathermvvm.models

data class WeatherBaseModel(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherData>,
    val message: Int
)