package com.example.weather.domain.useCases.weather

import com.example.weather.domain.models.TempUnit
import com.example.weather.domain.repositories.WeatherRepository
import javax.inject.Inject

class UpdateWeatherDataUseCase @Inject constructor(private val weatherRepo: WeatherRepository) {

   suspend operator fun invoke(unit : TempUnit = TempUnit.Celsius) {
        weatherRepo.updateWeathers(unit)
    }
}