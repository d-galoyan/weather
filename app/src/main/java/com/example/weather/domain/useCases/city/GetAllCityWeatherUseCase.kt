package com.example.weather.domain.useCases.city

import com.example.weather.domain.models.WeatherShort
import com.example.weather.domain.repositories.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCityWeatherUseCase @Inject constructor(
    private val weatherRepo: WeatherRepository
) {
    suspend operator fun invoke() : Flow<List<WeatherShort>> {
        return weatherRepo.getAlCitiesShortForecast()
    }
}