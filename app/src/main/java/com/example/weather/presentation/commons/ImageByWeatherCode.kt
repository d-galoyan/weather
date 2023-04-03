package com.example.weather.presentation.commons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.weather.R
import com.example.weather.domain.models.WeatherCondition

val images = mapOf(
    WeatherCondition.ClearSky.code to Pair(R.raw.clear_sky, 1f),
    WeatherCondition.MainlyClear.code to Pair(R.raw.mainly_sunny, 1f),
    WeatherCondition.PartlyCloudy.code to Pair(R.raw.patly_cloudy, 1f),
    WeatherCondition.Overcast.code to Pair(R.raw.overcast, 1f),

    WeatherCondition.Fog.code to Pair(R.raw.foggy, 1f),
    WeatherCondition.RimeFog.code to Pair(R.raw.foggy, 1f),

    WeatherCondition.LightDrizzle.code to Pair(R.raw.drizzle, 1f),
    WeatherCondition.ModerateDrizzle.code to Pair(R.raw.drizzle, 3f),
    WeatherCondition.StrongDrizzle.code to Pair(R.raw.drizzle, 4f),
    WeatherCondition.FreezingLightDrizzle.code to Pair(R.raw.drizzle, 1f),
    WeatherCondition.FreezingStrongDrizzle.code to Pair(R.raw.drizzle, 4f),

    WeatherCondition.LightRain.code to Pair(R.raw.rain, 1f),
    WeatherCondition.ModerateRain.code to Pair(R.raw.rain, 3f),
    WeatherCondition.HeavyRain.code to Pair(R.raw.rain, 4f),
    WeatherCondition.FreezingLightRain.code to Pair(R.raw.rain, 1f),
    WeatherCondition.FreezingHeavyRain.code to Pair(R.raw.rain, 4f),

    WeatherCondition.SnowGrains.code to Pair(R.raw.snow, 1f),
    WeatherCondition.LightSnow.code to Pair(R.raw.snow, 2f),
    WeatherCondition.ModerateSnow.code to Pair(R.raw.snow, 3f),
    WeatherCondition.HeavySnow.code to Pair(R.raw.snow, 4f),

    WeatherCondition.SlightShowers.code to Pair(R.raw.rain, 1f),
    WeatherCondition.ModerateShowers.code to Pair(R.raw.rain, 2f),
    WeatherCondition.ViolentShowers.code to Pair(R.raw.rain, 4f),

    WeatherCondition.LightSnowShowers.code to Pair(R.raw.snow, 1f),
    WeatherCondition.HeavySnowShowers.code to Pair(R.raw.snow, 3f),

    WeatherCondition.Thunderstorm.code to Pair(R.raw.thunderstorm, 1f),
    WeatherCondition.SlightThunderstormHail.code to Pair(R.raw.thunderstorm_hail, 2f),
    WeatherCondition.HeavyThunderstormHail.code to Pair(R.raw.thunderstorm_hail, 3f),

)

@Composable
fun ImageByWeatherCode(code: Int, modifier: Modifier = Modifier, isAnimated: Boolean = true) {

    val icon = images[code] ?: Pair(R.raw.clear_sky, 1f)

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(icon.first)
    )

    LottieAnimation(
        modifier = modifier,
        composition = composition,
        iterations = LottieConstants.IterateForever,
        speed = icon.second,
        isPlaying = isAnimated,
    )
}
