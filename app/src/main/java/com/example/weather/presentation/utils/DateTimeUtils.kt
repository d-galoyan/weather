package com.example.weather.presentation.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateTimeUtils(private val dateTime: String) {

    fun getTime(): String {
        return LocalDateTime.parse(dateTime).format(DateTimeFormatter.ofPattern("HH:mm"))
    }

    fun getDateTime(): String {
        return LocalDateTime.parse(dateTime).format(DateTimeFormatter.ofPattern("MMM dd, HH:mm"))
    }
}