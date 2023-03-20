package com.example.weather.domain.repositories

import com.example.weather.domain.models.City
import com.example.weather.domain.models.CityGeometry

interface PlacesRepository {
    suspend fun getCities(input : String) : List<City>
    suspend fun getGeometry(id : String) : CityGeometry
}