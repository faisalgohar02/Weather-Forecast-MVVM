package com.weatherforecast.weathermvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.weatherforecast.weathermvvm.commondata.WeatherCommon
import com.weatherforecast.weathermvvm.models.WeatherBaseModel
import com.weatherforecast.weathermvvm.weatherservice.WeatherServiceApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRepository   {
    var baseUrl = "http://api.openweathermap.org/"
    var exclude = "hourly"
    var isResponseGet = false

    private val weatherServiceApi: WeatherServiceApi =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build().create(WeatherServiceApi::class.java)

    private val forecastResponseLiveData: MutableLiveData<WeatherBaseModel> = MutableLiveData<WeatherBaseModel>()

    fun getWeatherForecast(lat: Double, lang: Double) {
        weatherServiceApi.getCurrentWeatherData(
            lat = lat,
            lon = lang,
            hourly = exclude,
            app_id = WeatherCommon.apiKey
        ).enqueue(object : Callback<WeatherBaseModel> {
            override fun onResponse(
                call: Call<WeatherBaseModel>,
                response: Response<WeatherBaseModel>
            ) {
                if (response.body() != null) {
                    forecastResponseLiveData.postValue(response.body()!!)
                    isResponseGet = true
                }
            }
            override fun onFailure(call: Call<WeatherBaseModel>, t: Throwable) {
                isResponseGet = false

            }
        })
    }

    fun getForecastResponseLiveData(): LiveData<WeatherBaseModel> {
        return forecastResponseLiveData
    }
}