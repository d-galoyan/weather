package com.example.weather.presentation.screens.details

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weather.domain.models.DayWeather
import com.example.weather.domain.models.Weather

@Composable
fun NextDaysScreen(weather: Weather) {

    LazyColumn {
        items(weather.days, key = { it.day }) { nextDay ->
            WeatherItem(
                nextDay = nextDay,
                windSpeedUnit = weather.windSpeedUnit,
                humidityUnit = weather.humidityUnit,
                pressureUnit = weather.pressureUnit
            )
            Divider()
        }
    }

}

@Composable
fun WeatherItem(
    nextDay: DayWeather,
    windSpeedUnit: String,
    humidityUnit: String,
    pressureUnit: String
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.animateContentSize(
            animationSpec = spring(
                stiffness = Spring.StiffnessLow
            )
        )
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        expanded = !expanded
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {

                PrintDayAndWeekDay(nextDay.day)
                Column {
                    Text(text = "${nextDay.dailyMax}${DegreeSymbol}")
                    Text(text = "${nextDay.nightMax}${DegreeSymbol}")
                }
            }
            if (expanded) {
                WeatherInfo(nextDay, windSpeedUnit, humidityUnit, pressureUnit)
            }
        }

    }

}
