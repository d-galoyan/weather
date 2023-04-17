package com.example.weather.domain.useCases.weather

import com.example.weather.domain.models.Weather
import com.example.weather.domain.repositories.WeatherRepository
import javax.inject.Inject

class GetWeatherDetailsUseCase @Inject constructor(
    private val weatherRepo: WeatherRepository
) {
    suspend operator fun invoke(id : String) : Weather {
        return weatherRepo.getWeather(id)
    }
}