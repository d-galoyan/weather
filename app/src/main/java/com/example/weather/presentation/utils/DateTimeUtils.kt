package com.example.weather.presentation.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


enum class DateTimeFormat(val format : String){
    Time("HH:mm"),
    DateTime("MMM dd, HH:mm")
}

fun formatDateTime(dateTime : String, dateTimeFormat : DateTimeFormat) : String{
    return LocalDateTime.parse(dateTime).format(DateTimeFormatter.ofPattern(dateTimeFormat.format))
}