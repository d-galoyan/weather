package com.example.weather.data.dataSourceImpls.places

import com.example.weather.data.dataSourceImpls.places.Geometry
import com.example.weather.data.dataSourceImpls.places.GooglePrediction
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