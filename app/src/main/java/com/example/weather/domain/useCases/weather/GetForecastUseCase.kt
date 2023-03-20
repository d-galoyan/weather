package com.example.weather.domain.useCases.weather

import com.example.weather.domain.models.CityGeometry
import com.example.weather.domain.models.TempUnit
import com.example.weather.domain.models.Weather
import com.example.weather.domain.repositories.WeatherRepository
import javax.inject.Inject


class GetForecastUseCase @Inject constructor(
    private val weatherRepo: WeatherRepository
) {
   suspend operator fun invoke(coordinates : CityGeometry, cityName : String, unit : TempUnit) : Weather {
        return weatherRepo.getForecast(coordinates, cityName, unit)
    }
}