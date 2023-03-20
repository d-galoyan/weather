package com.example.weather.domain.useCases.weather

import com.example.weather.domain.repositories.WeatherRepository
import javax.inject.Inject

class RemoveWeatherUseCase @Inject constructor(
    private val weatherRepo: WeatherRepository,
) {
    suspend operator fun invoke(id: Int) {
        weatherRepo.removeWeather(id)
    }
}