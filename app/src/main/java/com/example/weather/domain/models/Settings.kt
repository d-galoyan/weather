package com.example.weather.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Settings(
    val tempUnit: TempUnit = TempUnit.Celsius
)

enum class TempUnit(val value : String) {
    Celsius("celsius"),
    Fahrenheit("fahrenheit"),
}
