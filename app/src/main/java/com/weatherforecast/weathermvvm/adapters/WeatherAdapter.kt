package com.weatherforecast.weathermvvm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.weatherforecast.weathermvvm.R
import com.weatherforecast.weathermvvm.databinding.WeatherListItemsBinding
import com.weatherforecast.weathermvvm.models.WeatherBaseModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


class WeatherAdapter(private val context: Context) :
    RecyclerView.Adapter<WeatherAdapter.WeatherHolder>() {

    private var list: WeatherBaseModel? = null

    inner class WeatherHolder(val binding: WeatherListItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        return WeatherHolder(
            WeatherListItemsBinding.inflate(
                LayoutInflater.from(context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        holder.binding.run {

            dayTV.text =
                list?.list?.get(position)?.dt.let { it?.let { it1 -> getDay(it1.toDouble()) } }
                    .toString()
            maximumTemp.text = list?.list?.get(position)?.main?.temp?.roundToInt().toString() + "°"
            val lstValues: List<String> =
                list?.list?.get(position)?.dt_txt.toString().split(" ").map { it -> it.trim() }
            lstValues.forEach { it ->
                dateTxt.text = it.toString()
            }

            val imageURL = when (list?.list?.get(position)?.weather?.get(0)?.icon) {
                "01d" -> {
                    R.drawable.ic_01d
                }
                "02d" -> {
                    R.drawable.ic_02d
                }
                "03d" -> {
                    R.drawable.ic_03d
                }
                "04d" -> {
                    R.drawable.ic_04d
                }
                "09d" -> {
                    R.drawable.ic_09d
                }
                "10d" -> {
                    R.drawable.ic_10d
                }
                "11d" -> {
                    R.drawable.ic_11d
                }
                "13d" -> {
                    R.drawable.ic_13d
                }
                "01n" -> {
                    R.drawable.ic_01d
                }
                "02n" -> {
                    R.drawable.ic_02d
                }
                "03n" -> {
                    R.drawable.ic_03d
                }
                "04n" -> {
                    R.drawable.ic_04d
                }
                "09n" -> {
                    R.drawable.ic_09d
                }
                "10n" -> {
                    R.drawable.ic_10d
                }
                "11n" -> {
                    R.drawable.ic_11d
                }
                "13n" -> {
                    R.drawable.ic_13d
                }
                "50n" -> {
                    R.drawable.ic_50d
                }
                "50d" -> {
                    R.drawable.ic_50d
                }
                else -> {
                    R.drawable.ic_01d
                }
            }
            weatherImg.setImageResource(imageURL)
        }
    }


    fun updateList(list: WeatherBaseModel) {
        this.list = list
        notifyDataSetChanged()
    }

    private fun getDay(milli: Double): String {
        val sdf = SimpleDateFormat("EEEE", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = ((milli * 1000L).toLong())
        val date = Date(calendar.timeInMillis)
        return sdf.format(date)
    }


    override fun getItemCount(): Int = list?.list?.size ?: 0
}