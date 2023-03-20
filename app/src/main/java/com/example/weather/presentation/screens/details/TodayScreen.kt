package com.example.weather.presentation.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weather.domain.models.Weather
import com.example.weather.presentation.components.ImageByWeatherCode
import com.example.weather.presentation.components.PrintWeatherCondition
import com.example.weather.presentation.components.TemperatureInfo

@Composable
fun TodayScreen(weather: Weather) {

    val today = weather.days[0]

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                TemperatureInfo(weather)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    ImageByWeatherCode(
                        today.weatherCode,
                        Modifier.size(160.dp)
                    )
                    PrintWeatherCondition(today.weatherCode)
                }
            }
            Row {
                WeatherInfo(
                    today,
                    weather.windSpeedUnit,
                    weather.humidityUnit,
                    weather.pressureUnit
                )
            }
        }
    }
}

