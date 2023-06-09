package com.example.weather.domain.models

data class Settings(
    val id : Int = 0,
    val tempUnit: TempUnit = TempUnit.Celsius,
    val theme: ThemeMode = ThemeMode.Light,
)

enum class TempUnit(val value : String) {
    Celsius("celsius"),
    Fahrenheit("fahrenheit"),
}

enum class ThemeMode {
    Light,
    Dark,
}
