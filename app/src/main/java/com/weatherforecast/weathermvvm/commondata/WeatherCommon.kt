package com.weatherforecast.weathermvvm.commondata

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

object WeatherCommon {

    val apiKey="7a3f52f0f6718cdf74b7b56149be64e2"
    val latitude=33.626057
    val longitude=73.071442

    @RequiresApi(Build.VERSION_CODES.M)
    fun isInternetConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networks = connectivityManager.activeNetwork ?: return false
        val actiNetworks = connectivityManager.getNetworkCapabilities(networks) ?: return false
        return when {
            actiNetworks.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actiNetworks.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actiNetworks.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

}