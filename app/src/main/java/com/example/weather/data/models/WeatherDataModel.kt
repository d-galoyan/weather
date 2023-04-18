package com.example.weather.data.models

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    @SerializedName(value = "temperature")
    val temp: Float,
    @SerializedName(value = "windspeed")
    val windSpeed: Float,
    @SerializedName(value = "winddirection")
    val windDirection: Float,
)

data class WeatherDataModel(
    @SerializedName(value = "latitude")
    val lat: Float,
    @SerializedName(value = "longitude")
    val long: Float,
    @SerializedName(value = "current_weather")
    val currentWeather: CurrentWeather,
    @SerializedName(value = "hourly_units")
    val unit: WeatherUnit,
    @SerializedName(value = "daily_units")
    val dailyUnits: DailyUnit,
    val hourly: Hourly,
    val daily: Daily,
)

data class WeatherUnit(
    @SerializedName(value = "temperature_2m")
    val tempUnit: String,
    @SerializedName(value = "relativehumidity_2m")
    val humidityUnit: String,
    @SerializedName(value = "pressure_msl")
    val pressureUnit: String
)

data class DailyUnit(
    @SerializedName(value = "windspeed_10m_max")
    val windSpeedUnit: String
)

data class Hourly(
    val time: List<String>,
    @SerializedName(value = "temperature_2m")
    val temp: List<Float>,
    @SerializedName(value = "apparent_temperature")
    val apparentTemp: List<Float>,
    @SerializedName(value = "relativehumidity_2m")
    val humidity: List<Int>,
    @SerializedName(value = "pressure_msl")
    val pressure: List<Float>,
    @SerializedName(value = "weathercode")
    val weatherCode: List<Int>
)

data class Daily(
    @SerializedName(value = "time")
    val day: List<String>,
    val sunrise: List<String>,
    val sunset: List<String>,
    @SerializedName(value = "windspeed_10m_max")
    val windSpeed: List<Float>,
    @SerializedName(value = "temperature_2m_max")
    val maxTemp: List<Float>,
    @SerializedName(value = "temperature_2m_min")
    val minTemp: List<Float>,
    @SerializedName(value = "weathercode")
    val weatherCode: List<Int>
)