package com.example.weather.presentation.commons

import androidx.annotation.StringRes
import com.example.weather.R
import com.example.weather.domain.models.WeatherCondition

val DefaultIconDetails = IconDetails(R.raw.sun, R.string.clear_sky, 1f)

val IconsMap = mapOf(
    WeatherCondition.ClearSky.code to DefaultIconDetails,
    WeatherCondition.MainlyClear.code to IconDetails(R.raw.mainly_sunny, R.string.mainly_clear, 1f),
    WeatherCondition.PartlyCloudy.code to IconDetails(
        R.raw.patly_cloudy,
        R.string.partially_cloudy,
        1f
    ),
    WeatherCondition.Overcast.code to IconDetails(R.raw.overcast, R.string.overcast, 1f),

    WeatherCondition.Fog.code to IconDetails(R.raw.foggy, R.string.fog, 1f),
    WeatherCondition.RimeFog.code to IconDetails(R.raw.foggy, R.string.rime_fog, 1f),

    WeatherCondition.LightDrizzle.code to IconDetails(R.raw.drizzle, R.string.light_drizzle, 1f),
    WeatherCondition.ModerateDrizzle.code to IconDetails(
        R.raw.drizzle,
        R.string.moderate_drizzle,
        3f
    ),
    WeatherCondition.StrongDrizzle.code to IconDetails(R.raw.drizzle, R.string.strong_drizzle, 4f),
    WeatherCondition.FreezingLightDrizzle.code to IconDetails(
        R.raw.drizzle,
        R.string.freezing_light_drizzle,
        1f
    ),
    WeatherCondition.FreezingStrongDrizzle.code to IconDetails(
        R.raw.drizzle,
        R.string.freezing_strong_drizzle,
        4f
    ),

    WeatherCondition.LightRain.code to IconDetails(R.raw.rain, R.string.light_rain, 1f),
    WeatherCondition.ModerateRain.code to IconDetails(R.raw.rain, R.string.moderat_rain, 3f),
    WeatherCondition.HeavyRain.code to IconDetails(R.raw.rain, R.string.heavy_rain, 4f),
    WeatherCondition.FreezingLightRain.code to IconDetails(
        R.raw.rain,
        R.string.freezing_light_rain,
        1f
    ),
    WeatherCondition.FreezingHeavyRain.code to IconDetails(
        R.raw.rain,
        R.string.freezing_heavy_rain,
        4f
    ),

    WeatherCondition.SnowGrains.code to IconDetails(R.raw.snow, R.string.snow_grains, 1f),
    WeatherCondition.LightSnow.code to IconDetails(R.raw.snow, R.string.light_snow, 2f),
    WeatherCondition.ModerateSnow.code to IconDetails(R.raw.snow, R.string.moderate_snow, 3f),
    WeatherCondition.HeavySnow.code to IconDetails(R.raw.snow, R.string.heavy_snow, 4f),

    WeatherCondition.SlightShowers.code to IconDetails(R.raw.rain, R.string.slight_showers, 1f),
    WeatherCondition.ModerateShowers.code to IconDetails(R.raw.rain, R.string.moderate_showers, 2f),
    WeatherCondition.ViolentShowers.code to IconDetails(R.raw.rain, R.string.violent_showers, 4f),

    WeatherCondition.LightSnowShowers.code to IconDetails(
        R.raw.snow,
        R.string.light_snow_showers,
        1f
    ),
    WeatherCondition.HeavySnowShowers.code to IconDetails(
        R.raw.snow,
        R.string.heavy_snow_showers,
        3f
    ),

    WeatherCondition.Thunderstorm.code to IconDetails(
        R.raw.thunderstorm,
        R.string.thunderstorm,
        1f
    ),
    WeatherCondition.SlightThunderstormHail.code to IconDetails(
        R.raw.thunderstorm_hail,
        R.string.slight_thunderstorm_hail,
        2f
    ),
    WeatherCondition.HeavyThunderstormHail.code to IconDetails(
        R.raw.thunderstorm_hail,
        R.string.heavy_thunderstorm_hail,
        3f
    ),
)

data class IconDetails(
    val icon: Int,
    @StringRes val desc: Int,
    val animationSpeed: Float
)