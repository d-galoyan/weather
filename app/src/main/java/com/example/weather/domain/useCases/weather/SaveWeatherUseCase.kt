package com.example.weather.domain.useCases.weather

import com.example.weather.domain.models.City
import com.example.weather.domain.models.TempUnit
import com.example.weather.domain.repositories.PlacesRepository
import com.example.weather.domain.repositories.WeatherRepository
import javax.inject.Inject

class SaveWeatherUseCase @Inject constructor(
    private val weatherRepo: WeatherRepository,
    private val cityRepo: PlacesRepository,
) {
    suspend operator fun invoke(city: City, unit: TempUnit) {
        val coordinates = cityRepo.getGeometry(city.id)
        val weather = weatherRepo.getForecast(coordinates, city, unit)
        weatherRepo.saveWeather(weather)
    }
}