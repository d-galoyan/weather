package com.example.weather.presentation.commons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun ImageByWeatherCode(code: Int, modifier: Modifier = Modifier, isAnimated: Boolean = true) {

    val icon = IconsMap[code] ?: DefaultIconDetails

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(icon.icon)
    )

    LottieAnimation(
        modifier = modifier,
        composition = composition,
        iterations = LottieConstants.IterateForever,
        speed = icon.animationSpeed,
        isPlaying = isAnimated,
    )
}
