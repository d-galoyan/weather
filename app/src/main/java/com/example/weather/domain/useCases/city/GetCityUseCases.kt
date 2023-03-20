package com.example.weather.domain.useCases.city

import com.example.weather.domain.models.City
import com.example.weather.domain.repositories.PlacesRepository
import javax.inject.Inject

class GetCityUseCase @Inject constructor(private val placesRepo: PlacesRepository) {
    suspend operator fun invoke(input: String): List<City> {
        return placesRepo.getCities(input)
    }
}