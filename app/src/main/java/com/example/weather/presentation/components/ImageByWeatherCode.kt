package com.example.weather.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.weather.R
import com.example.weather.domain.models.WeatherCondition

@Composable
fun ImageByWeatherCode(code: Int, modifier: Modifier = Modifier) {

    val images = mapOf(
        WeatherCondition.Sunny.code to R.drawable.sunny,
        WeatherCondition.Cloudy.code to R.drawable.cloudy,
        WeatherCondition.MostlyCloudy.code to R.drawable.mostly_cloudy,
        WeatherCondition.PartiallyCloudy.code to R.drawable.partially_cloudy,
        WeatherCondition.Rain.code to R.drawable.rain,
        WeatherCondition.MostlyRainy.code to R.drawable.mostly_rainy,
        WeatherCondition.PartiallyRainy.code to R.drawable.partially_rainy,
        WeatherCondition.Showers.code to R.drawable.rain,
        WeatherCondition.MostlyShower.code to R.drawable.mostly_rainy,
        WeatherCondition.PartiallyShower.code to R.drawable.partially_rainy,
    )

    val imageId = images[code] ?: R.drawable.sunny

    Image(
        painter = painterResource(id = imageId),
        modifier = modifier,
        contentDescription = null
    )
}