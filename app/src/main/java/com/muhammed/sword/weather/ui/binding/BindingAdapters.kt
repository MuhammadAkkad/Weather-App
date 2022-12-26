package com.muhammed.sword.weather.ui.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.muhammed.sword.weather.domain.weather.CurrentWeatherModel
import com.muhammed.sword.weather.domain.weather.WeatherDataHourlyModel
import com.muhammed.sword.weather.domain.weather.WeatherType
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class BindingAdapters {

    companion object {

        @BindingAdapter("android:iconRes")
        @JvmStatic
        fun setImageViewResource(imageView: ImageView, weatherType: WeatherType) {
            try {
                imageView.setImageResource(weatherType.iconRes)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @BindingAdapter("android:setHour")
        @JvmStatic
        fun setTextViewHour(textView: TextView, weatherData: WeatherDataHourlyModel) {
            try {
                textView.text = if (LocalDateTime.now().hour == weatherData.time.hour) {
                    "Now"
                } else {
                    weatherData.time.format(
                        DateTimeFormatter.ofPattern("Ha")
                    ).toString()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @BindingAdapter("android:setTemp")
        @JvmStatic
        fun setTextViewTemp(textView: TextView, temperature: Double?) {
            try {
                if (temperature != null) {
                    val temp = temperature.toInt().toString() + "°"
                    textView.text = temp
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @BindingAdapter("android:setHighAndLow")
        @JvmStatic
        fun setTextViewHourTempHandL(textView: TextView, weatherData: CurrentWeatherModel?) {
            try {
                if (weatherData != null) {
                    val highTemp = weatherData.temperatureMax.toInt()
                    val minTemp = weatherData.temperatureMin.toInt()
                    val text = "H:$highTemp° L:$minTemp°"
                    textView.text = text
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @BindingAdapter("android:dayName")
        @JvmStatic
        fun setTextVieDayName(textView: TextView, date: LocalDate?) {
            try {
                if (date != null) {
                    val now = LocalDateTime.now()
                    val dayName = if (now.dayOfWeek == date.dayOfWeek)
                        "Today"
                    else if (now.plusDays(1).dayOfWeek == date.dayOfWeek)
                        "Tomorrow"
                    else {
                        date.dayOfWeek.toString()
                    }
                    textView.text = dayName.lowercase().replaceFirstChar { it.uppercaseChar() }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}