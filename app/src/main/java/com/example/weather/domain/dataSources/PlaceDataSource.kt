package com.example.weather.domain.dataSources

import com.example.weather.domain.models.City
import com.example.weather.domain.models.CityGeometry

interface PlaceDataSource {

    suspend fun getPredictions(input: String): List<City>
    suspend fun getGeometry(placeId: String): CityGeometry

}