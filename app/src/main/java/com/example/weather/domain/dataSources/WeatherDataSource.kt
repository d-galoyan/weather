package com.example.weather.domain.dataSources

import com.example.weather.domain.models.Weather
import com.example.weather.domain.models.WeatherShort
import kotlinx.coroutines.flow.Flow

interface WeatherDataSource {
    suspend fun <R> withTransaction(block: suspend () -> R): R
    suspend fun getWeather(id : String): Weather
    suspend fun removeWeather(id : String) : Weather
    suspend fun getWeathersShort(): Flow<List<WeatherShort>>
    suspend fun saveWeather(weather: Weather)
}