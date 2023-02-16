package com.weatherforecast.weathermvvm.models

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Double,
    val sunset: Double,
    val timezone: Int
)