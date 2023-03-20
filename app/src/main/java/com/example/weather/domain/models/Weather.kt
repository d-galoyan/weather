package com.example.weather.domain.models

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

enum class WeatherCondition(val code : Int) {
    Sunny(0),
    PartiallyCloudy(1),
    MostlyCloudy(2),
    Cloudy(3),
    PartiallyRainy(61),
    MostlyRainy(62),
    Rain(63),
    PartiallyShower(80),
    MostlyShower(81),
    Showers(82)
}