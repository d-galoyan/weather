package com.example.weather.domain.models

data class Settings(
    val id : Int = 0,
    val tempUnit: TempUnit = TempUnit.Celsius
)

enum class TempUnit(val value : String) {
    Celsius("celsius"),
    Fahrenheit("fahrenheit"),
}
