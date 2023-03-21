package com.example.weather.presentation.commons

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.weather.R
import com.example.weather.domain.models.Weather

@Composable
fun TemperatureInfo(weather: Weather) {

    val tempUnit = weather.tempUnit

    Column {
        Text(
            text = "${stringResource(id = R.string.day)} ${weather.dailyMax}${tempUnit} -" +
                    " ${stringResource(id = R.string.night)} ${weather.nightMax}${tempUnit}"
        )
        Text(
            text = "${weather.currentTemperature}${tempUnit}",
            style = MaterialTheme.typography.h2,
        )
        Text(text = "${stringResource(id = R.string.fells_like)} ${weather.apparentTemp}${tempUnit}")
    }
}