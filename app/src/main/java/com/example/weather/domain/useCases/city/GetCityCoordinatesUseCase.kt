package com.example.weather.domain.useCases.city

import com.example.weather.domain.models.CityGeometry
import com.example.weather.domain.repositories.PlacesRepository
import javax.inject.Inject

class GetCityCoordinatesUseCase @Inject constructor(private val cityRepo: PlacesRepository) {
    suspend operator fun invoke(id: String) : CityGeometry {
        return cityRepo.getGeometry(id)
    }
}