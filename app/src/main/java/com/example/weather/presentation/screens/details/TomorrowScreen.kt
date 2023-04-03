package com.example.weather.presentation.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weather.data.mappers.toWeatherShort
import com.example.weather.domain.models.Weather
import com.example.weather.presentation.commons.DayNightTempInfo
import com.example.weather.presentation.commons.ImageByWeatherCode
import com.example.weather.presentation.commons.PrintWeatherCondition

@Composable
fun TomorrowScreen(weather: Weather) {
    Box(
        modifier = Modifier.padding(16.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight()
        ) {
            val tomorrow = weather.days[1]

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(top = 32.dp)) {
                    PrintDayAndWeekDay(tomorrow.day)
                    DayNightTempInfo(weather.toWeatherShort())
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    ImageByWeatherCode(
                        tomorrow.weatherCode,
                        Modifier.size(160.dp),
                        false
                    )
                    PrintWeatherCondition(tomorrow.weatherCode)
                }
            }

            Row {
                WeatherInfo(
                    tomorrow,
                    weather.windSpeedUnit,
                    weather.humidityUnit,
                    weather.pressureUnit
                )
            }
        }

    }
}

