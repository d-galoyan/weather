package com.example.weather.domain.models

data class Weather(
    val id: String,
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
    val days: List<DayWeather>,
    val position : Int
)

data class WeatherShort(
    val id: String,
    val cityName: String,
    val dailyMax: Int,
    val nightMax: Int,
    val lat: Float,
    val long: Float,
    val tempUnit: String,
    val weatherCode: Int,
    val position: Int,
)

data class Hourly(
    val time: String,
    val temp: Int,
    val apparentTemp: Int,
    val humidity: Int,
    val pressure: Float,
    val weatherCode: Int,
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

enum class WeatherCondition(val code: Int) {
    ClearSky(0),

    MainlyClear(1),
    PartlyCloudy(2),
    Overcast(3),

    Fog(45),
    RimeFog(48),

    LightDrizzle(51),
    ModerateDrizzle(53),
    StrongDrizzle(55),
    FreezingLightDrizzle(56),
    FreezingStrongDrizzle(57),

    LightRain(61),
    ModerateRain(63),
    HeavyRain(65),
    FreezingLightRain(65),
    FreezingHeavyRain(67),

    LightSnow(71),
    ModerateSnow(73),
    HeavySnow(75),

    SnowGrains(77),

    SlightShowers(80),
    ModerateShowers(81),
    ViolentShowers(82),

    LightSnowShowers(85),
    HeavySnowShowers(86),

    Thunderstorm(95),
    SlightThunderstormHail(96),
    HeavyThunderstormHail(99),

}