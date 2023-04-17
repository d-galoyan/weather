package com.example.weather.domain.dataSources

import com.example.weather.domain.models.City
import com.example.weather.domain.models.CityGeometry
import com.example.weather.domain.models.TempUnit
import com.example.weather.domain.models.Weather

interface WeatherForecastDataSource {
   suspend fun getForecast(coordinates: CityGeometry, city : City, unit : TempUnit): Weather
}