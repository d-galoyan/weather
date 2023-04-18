package com.example.weather.data.repos

import com.example.weather.domain.dataSources.PlaceDataSource
import com.example.weather.domain.models.City
import com.example.weather.domain.models.CityGeometry
import com.example.weather.domain.repositories.PlacesRepository
import javax.inject.Inject

class PlacesRepo @Inject constructor(
    private val placeDataSource: PlaceDataSource,
) : PlacesRepository {

    override suspend fun getCities(input: String): List<City> {
        return placeDataSource.getPredictions(input = input)
    }

    override suspend fun getGeometry(id: String): CityGeometry {
        return placeDataSource.getGeometry(placeId = id)
    }
}