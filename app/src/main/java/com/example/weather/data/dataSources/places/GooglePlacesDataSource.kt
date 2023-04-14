package com.example.weather.data.dataSources.places

import com.example.weather.data.mappers.toCity
import com.example.weather.data.mappers.toCityGeometry
import com.example.weather.domain.dataSources.PlaceDataSource
import com.example.weather.domain.models.City
import com.example.weather.domain.models.CityGeometry
import javax.inject.Inject

class GooglePlacesDataSource @Inject constructor(
    private val api: GooglePlacesApiService,
) : PlaceDataSource {

    override suspend fun getPredictions(input: String): List<City> {
       return api.getPredictions(input = input).predictions.map { it.toCity() }
    }

    override suspend fun getGeometry(placeId: String): CityGeometry {
       return api.getGeometry(placeId = placeId).result.geometry.toCityGeometry()
    }
}