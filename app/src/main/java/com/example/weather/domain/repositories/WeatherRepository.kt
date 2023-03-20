package com.example.weather.domain.repositories

import com.example.weather.domain.models.CityGeometry
import com.example.weather.domain.models.TempUnit
import com.example.weather.domain.models.Weather
import com.example.weather.domain.models.WeatherShort
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getForecast(coordinates: CityGeometry , cityName : String, unit : TempUnit): Weather
    suspend fun getWeather(id : Int): Flow<Weather>
    suspend fun removeWeather(id : Int)
    suspend fun getAlCitiesShortForecast(): Flow<List<WeatherShort>>
    suspend fun saveWeather(weather: Weather)
    suspend fun updateWeathers(unit : TempUnit)
}