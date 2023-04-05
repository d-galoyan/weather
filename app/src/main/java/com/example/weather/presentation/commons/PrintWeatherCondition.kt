package com.example.weather.presentation.commons

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun PrintWeatherCondition(code: Int) {
    val desc = IconsMap[code]?.desc ?: DefaultIconDetails.desc

    Text(
        text = stringResource(id = desc),
        style = MaterialTheme.typography.body2
    )
}