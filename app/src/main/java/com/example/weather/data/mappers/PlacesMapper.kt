package com.example.weather.data.mappers

import com.example.weather.data.models.Geometry
import com.example.weather.data.models.GooglePrediction
import com.example.weather.domain.models.City
import com.example.weather.domain.models.CityGeometry

fun GooglePrediction.toCity(): City {
    return City(
        id = placeId,
        name = description,
    )
}

fun Geometry.toCityGeometry(): CityGeometry {
    return CityGeometry(
        lng = location.lng,
        lat = location.lat,
    )
}