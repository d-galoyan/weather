package com.example.weather.presentation.commons

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.weather.domain.models.WeatherCondition

@Composable
fun PrintWeatherCondition(code: Int) {
    val condition = WeatherCondition.values()
        .find { it.code == code } ?: WeatherCondition.ClearSky

    Text(
        text = stringResource(id = condition.desc),
        style = MaterialTheme.typography.body2
    )
}