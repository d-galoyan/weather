package com.example.weather.data.repos

import androidx.room.Transaction
import com.example.weather.domain.dataSources.WeatherDataSource
import com.example.weather.domain.dataSources.WeatherForecastDataSource
import com.example.weather.domain.models.City
import com.example.weather.domain.models.CityGeometry
import com.example.weather.domain.models.TempUnit
import com.example.weather.domain.models.Weather
import com.example.weather.domain.models.WeatherShort
import com.example.weather.domain.repositories.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class WeatherRepo @Inject constructor(
    private val weatherDataSource: WeatherDataSource,
    private val weatherForecastApiDataSource: WeatherForecastDataSource
) : WeatherRepository {

    override suspend fun getWeather(id: String): Flow<Weather> {
        return weatherDataSource.getWeather(id)
    }

    override suspend fun removeWeather(id: String): Weather {
        return weatherDataSource.removeWeather(id)
    }

    override suspend fun getAlCitiesShortForecast(): Flow<List<WeatherShort>> {
        return weatherDataSource.getWeathersShort()
    }

    @Transaction
    override suspend fun fetchForecastAndSaveWeather(
        coordinates: CityGeometry,
        city: City,
        unit: TempUnit
    ) {
        weatherDataSource.withTransaction {
            val weather = weatherForecastApiDataSource.getForecast(coordinates, city, unit)
            saveWeather(weather)
        }
    }

    override suspend fun saveWeather(weather: Weather) {
        weatherDataSource.saveWeather(weather)
    }

    @Transaction
    override suspend fun updateWeathers(unit: TempUnit) {
        weatherDataSource.withTransaction {
            val weatherShort = weatherDataSource.getWeathersShort().first()

            weatherShort.forEach {
                val weather = weatherForecastApiDataSource.getForecast(
                    CityGeometry(
                        lng = it.long,
                        lat = it.lat,
                    ),
                    City(
                        id = it.id,
                        name = it.cityName
                    ),
                    unit = unit
                )
                saveWeather(weather = weather.copy(id = it.id))
            }
        }
    }
}