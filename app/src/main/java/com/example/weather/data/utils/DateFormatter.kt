package com.example.weather.data.utils

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class DateFormatter(
    formatPattern: String = "yyyy-MM-dd",
    private var currentDate : LocalDate = LocalDate.now()
) {

    private val formatter = DateTimeFormatter.ofPattern(formatPattern)
    private var period: Period = Period.of(0, 0, 0)

    fun getCurrentFormattedDate(): String {
        return LocalDate.now().format(formatter)
    }

    fun addDays(days: Int): DateFormatter {
        period = Period.of(0, 0, days)
        val date =
            LocalDate.of(currentDate.year, currentDate.month, currentDate.dayOfMonth)
        currentDate = date.plus(period)
        return this
    }

    fun getFormattedDate(): String {
        return currentDate.format(formatter)
    }

}