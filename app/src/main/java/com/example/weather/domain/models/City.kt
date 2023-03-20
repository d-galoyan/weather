package com.example.weather.domain.models

data class City(
    val id: String,
    val name: String,
)

data class CityGeometry (
    val lng: Float,
    val lat: Float,
)
