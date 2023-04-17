package com.example.weather.domain.repositories

import com.example.weather.domain.models.City
import com.example.weather.domain.models.CityGeometry
import com.example.weather.domain.models.TempUnit
import com.example.weather.domain.models.Weather
import com.example.weather.domain.models.WeatherShort
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeather(id : String): Flow<Weather>
    suspend fun removeWeather(id : String) : Weather
    suspend fun getAlCitiesShortForecast(): Flow<List<WeatherShort>>
    suspend fun fetchForecastAndSaveWeather(coordinates: CityGeometry, city : City, unit : TempUnit)
    suspend fun saveWeather(weather : Weather)
    suspend fun updateWeathers(unit : TempUnit)
}