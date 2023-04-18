package com.example.weather.data.utils

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class DateFormatter(
    formatPattern: String = "yyyy-MM-dd",
) {

    private val formatter = DateTimeFormatter.ofPattern(formatPattern)

    fun getCurrentFormattedDate(): String {
        return LocalDate.now().format(formatter)
    }

    fun getFormattedSinceTodayDays(days: Int) : String{
        var currentDate : LocalDate = LocalDate.now()
        val period = Period.of(0, 0, days)
        val date =
            LocalDate.of(currentDate.year, currentDate.month, currentDate.dayOfMonth)
        currentDate = date.plus(period)
        return currentDate.format(formatter)
    }
}