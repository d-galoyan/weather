package com.example.weather.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeather(
    @SerialName(value = "temperature")
    val temp: Float,
    @SerialName(value = "windspeed")
    val windSpeed: Float,
    @SerialName(value = "winddirection")
    val windDirection: Float,
)

@Serializable
data class WeatherDataModel(
    @SerialName(value = "latitude")
    val lat: Float,
    @SerialName(value = "longitude")
    val long: Float,
    @SerialName(value = "current_weather")
    val currentWeather: CurrentWeather,
    @SerialName(value = "hourly_units")
    val unit: Unit,
    @SerialName(value = "daily_units")
    val dailyUnits: DailyUnit,
    val hourly: Hourly,
    val daily: Daily,
)

@Serializable
data class Unit(
    @SerialName(value = "temperature_2m")
    val tempUnit: String,
    @SerialName(value = "relativehumidity_2m")
    val humidityUnit: String,
    @SerialName(value = "pressure_msl")
    val pressureUnit: String
)

@Serializable
data class DailyUnit(
    @SerialName(value = "windspeed_10m_max")
    val windSpeedUnit: String
)

@Serializable
data class Hourly(
    val time: List<String>,
    @SerialName(value = "temperature_2m")
    val temp: List<Float>,
    @SerialName(value = "apparent_temperature")
    val apparentTemp: List<Float>,
    @SerialName(value = "relativehumidity_2m")
    val humidity: List<Int>,
    @SerialName(value = "pressure_msl")
    val pressure: List<Float>,
    @SerialName(value = "weathercode")
    val weatherCode: List<Int>
)

@Serializable
data class Daily(
    @SerialName(value = "time")
    val day: List<String>,
    val sunrise: List<String>,
    val sunset: List<String>,
    @SerialName(value = "windspeed_10m_max")
    val windSpeed: List<Float>,
    @SerialName(value = "temperature_2m_max")
    val maxTemp: List<Float>,
    @SerialName(value = "temperature_2m_min")
    val minTemp: List<Float>,
    @SerialName(value = "weathercode")
    val weatherCode: List<Int>
)