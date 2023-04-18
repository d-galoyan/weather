package com.example.weather.presentation.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weather.R
import com.example.weather.domain.models.DayWeather
import com.example.weather.presentation.commons.ImageByWeatherCode
import com.example.weather.presentation.utils.DateTimeFormat
import com.example.weather.presentation.utils.formatDateTime

@Composable
fun WeatherInfo(
    nextDay: DayWeather,
    windSpeedUnit: String,
    humidityUnit: String,
    pressureUnit: String
) {

    fun timeFormat (dateTime : String) : String {
       return formatDateTime(dateTime, DateTimeFormat.Time)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(end = 128.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(id = R.string.wind))
                Text(text = "${nextDay.windSpeed} $windSpeedUnit")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = stringResource(id = R.string.humidity))
                Text(
                    text = "${nextDay.hourly[0].humidity}$humidityUnit",
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = stringResource(id = R.string.pressure))
                Text(text = "${nextDay.hourly[0].pressure} $pressureUnit")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = "${stringResource(id = R.string.sunrise)}/${stringResource(id = R.string.sunset)}")
                Text(text = "${timeFormat(nextDay.sunrise)}, ${timeFormat(nextDay.sunset)}")
            }
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        ) {
            items(nextDay.hourly, key = { it.time }) {
                Column(
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "${it.temp}${DegreeSymbol}")
                    ImageByWeatherCode(
                        it.weatherCode,
                        Modifier.size(32.dp),
                        false
                    )
                    Text(text =  timeFormat(it.time))
                }
            }
        }
    }
}
