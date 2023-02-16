package com.weatherforecast.weathermvvm.weatherservice

import com.weatherforecast.weathermvvm.models.WeatherBaseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherServiceApi {

    @GET("data/2.5/forecast?")
    fun getCurrentWeatherData(@Query("lat") lat: Double,
                              @Query("lon") lon: Double,
                              @Query("exclude") hourly:String,
                              @Query(value = "units") units: String = "metric",
                              @Query("APPID") app_id: String): Call<WeatherBaseModel>
}