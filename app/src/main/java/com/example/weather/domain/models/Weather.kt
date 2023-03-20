package com.example.weather.domain.models

import androidx.annotation.StringRes
import com.example.weather.R

data class Weather(
    val id : Int,
    val cityName: String,
    val dailyMax: Int,
    val nightMax: Int,
    val lat: Float,
    val long: Float,
    val currentTemperature: Int,
    val apparentTemp: Int,
    val tempUnit: String,
    val windSpeedUnit: String,
    val humidityUnit: String,
    val pressureUnit: String,
    val days: List<DayWeather>
)

data class WeatherShort(
    val id : Int,
    val cityName: String,
    val dailyMax: Int,
    val nightMax: Int,
    val lat: Float,
    val long: Float,
    val tempUnit: String,
    val weatherCode: Int,
)

data class Hourly(
    val time : String,
    val temp : Int,
    val apparentTemp : Int,
    val humidity : Int,
    val pressure : Float,
    val weatherCode : Int,
)

data class DayWeather(
    val day: String,
    val dailyMax: Int,
    val nightMax: Int,
    val windSpeed: Float,
    val sunrise: String,
    val sunset: String,
    val weatherCode: Int,
    val hourly: List<Hourly>,
)

enum class WeatherCondition(val code : Int, @StringRes val desc : Int) {
    Sunny(0, R.string.sunny),
    PartiallyCloudy(1, R.string.partially_cloudy),
    MostlyCloudy(2, R.string.mostly_cloudy),
    Cloudy(3, R.string.cloudy),
    PartiallyRainy(61, R.string.partially_rainy),
    MostlyRainy(62, R.string.mostly_cloudy),
    Rain(63, R.string.rain),
    PartiallyShower(80, R.string.partially_showers),
    MostlyShower(81, R.string.mostly_showers),
    Showers(82, R.string.showers)
}