package com.example.weather.domain.dataSources

import com.example.weather.domain.models.CityGeometry
import com.example.weather.domain.models.TempUnit
import com.example.weather.domain.models.Weather
import com.example.weather.domain.models.WeatherShort
import kotlinx.coroutines.flow.Flow

interface WeatherDataSource {

    suspend fun getForecast(coordinates: CityGeometry, cityName : String, unit : TempUnit): Weather
    suspend fun getWeather(id : String): Flow<Weather>
    suspend fun removeWeather(id : String)
    suspend fun getAlCitiesShortForecast(): Flow<List<WeatherShort>>
    suspend fun saveWeather(weather: Weather)
    suspend fun updateWeathers(unit : TempUnit)
}