package com.example.weather.domain.models

import androidx.annotation.StringRes
import com.example.weather.R

data class Weather(
    val id : String,
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
    val id : String,
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
    ClearSky(0, R.string.sunny),

    MainlyClear(1, R.string.partially_cloudy),
    PartlyCloudy(2, R.string.partially_cloudy),
    Overcast(3, R.string.mostly_cloudy),

    Fog(45, R.string.mostly_cloudy),
    RimeFog(48, R.string.mostly_cloudy),

    LightDrizzle(51, R.string.mostly_cloudy),
    ModerateDrizzle(53, R.string.mostly_cloudy),
    StrongDrizzle(55, R.string.mostly_cloudy),
    FreezingLightDrizzle(56, R.string.mostly_cloudy),
    FreezingStrongDrizzle(57, R.string.mostly_cloudy),

    LightRain(61, R.string.mostly_cloudy),
    ModerateRain(63, R.string.mostly_cloudy),
    HeavyRain(65, R.string.mostly_cloudy),
    FreezingLightRain(65, R.string.mostly_cloudy),
    FreezingHeavyRain(67, R.string.mostly_cloudy),

    LightSnow(71, R.string.mostly_cloudy),
    ModerateSnow(73, R.string.mostly_cloudy),
    HeavySnow(75, R.string.mostly_cloudy),

    SnowGrains(77, R.string.mostly_cloudy),

    SlightShowers(80, R.string.mostly_cloudy),
    ModerateShowers(81, R.string.mostly_cloudy),
    ViolentShowers(82, R.string.mostly_cloudy),

    LightSnowShowers(85, R.string.mostly_cloudy),
    HeavySnowShowers(86, R.string.mostly_cloudy),

    Thunderstorm(95, R.string.mostly_cloudy),
    SlightThunderstormHail(96, R.string.mostly_cloudy),
    HeavyThunderstormHail(99, R.string.mostly_cloudy),

}