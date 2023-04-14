package com.example.weather.domain.useCases.weather

import com.example.weather.domain.models.City
import com.example.weather.domain.models.TempUnit
import com.example.weather.domain.repositories.WeatherRepository
import com.example.weather.domain.useCases.city.GetCityCoordinatesUseCase
import javax.inject.Inject

class SaveWeatherUseCase @Inject constructor(
    private val weatherRepo: WeatherRepository,
    private val getForecast: GetForecastUseCase,
    private val getCityCoordinates: GetCityCoordinatesUseCase,
) {
    suspend operator fun invoke(city: City, unit: TempUnit) {
        val coordinates = getCityCoordinates(city.id)
        val weather = getForecast(coordinates, city, unit)
        weatherRepo.saveWeather(weather)
    }
}