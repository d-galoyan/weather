package com.example.weather.presentation.screens.details

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.weather.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun PrintDayAndWeekDay(day: String) {

    val dayTime = when (val date = LocalDate.parse(day)) {
        LocalDate.now() -> stringResource(id = R.string.today)
        else -> "${
            date.dayOfWeek.toString().lowercase().replaceFirstChar { it.uppercase() }
        }, ${date.format(DateTimeFormatter.ofPattern("MMM dd"))}"
    }

    Text(text = dayTime)
}