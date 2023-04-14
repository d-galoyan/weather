package com.example.weather.data.repos

import com.example.weather.domain.dataSources.WeatherDataSource
import com.example.weather.domain.models.City
import com.example.weather.domain.models.CityGeometry
import com.example.weather.domain.models.TempUnit
import com.example.weather.domain.models.Weather
import com.example.weather.domain.models.WeatherShort
import com.example.weather.domain.repositories.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepo @Inject constructor(private val weatherDataSource: WeatherDataSource) :
    WeatherRepository {
    override suspend fun getForecast(
        coordinates: CityGeometry,
        city: City,
        unit: TempUnit
    ): Weather {
        return weatherDataSource.getForecast(coordinates, city, unit)
    }

    override suspend fun getWeather(id: String): Flow<Weather> {
        return weatherDataSource.getWeather(id)
    }

    override suspend fun removeWeather(id: String) : Weather {
        return weatherDataSource.removeWeather(id)
    }

    override suspend fun getAlCitiesShortForecast(): Flow<List<WeatherShort>> {
        return weatherDataSource.getAlCitiesShortForecast()
    }

    override suspend fun saveWeather(weather: Weather) {
       weatherDataSource.saveWeather(weather)
    }

    override suspend fun updateWeathers(unit: TempUnit) {
        weatherDataSource.updateWeathers(unit)
    }
}