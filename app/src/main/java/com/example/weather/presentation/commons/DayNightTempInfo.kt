package com.example.weather.presentation.commons

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.domain.models.WeatherShort

@Composable
fun DayNightTempInfo(weather: WeatherShort) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 16.dp),
    ) {
        Text(
            text = stringResource(R.string.day),
            style = MaterialTheme.typography.body1,
        )
        Text(
            text = " ${weather.dailyMax}",
            fontSize = 24.sp,
        )
        Text(
            text = weather.tempUnit,
            fontSize = 16.sp,
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Top)
        )
        Text(
            text = " - ${stringResource(R.string.night)} ",
            style = MaterialTheme.typography.body1,
        )
        Text(
            text = "${weather.nightMax}",
            fontSize = 24.sp,
            color = MaterialTheme.colors.onSurface
        )
        Text(
            text = weather.tempUnit,
            fontSize = 16.sp,
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Top)
        )
    }
}