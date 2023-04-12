package com.example.weather.data.repos

import com.example.weather.domain.models.CityGeometry
import com.example.weather.domain.models.TempUnit
import com.example.weather.domain.models.Weather
import com.example.weather.domain.models.WeatherShort
import com.example.weather.domain.repositories.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

val fakeWeather = Weather(
    "",
    "",
    0,
    0,
    0f,
    0f,
    0,
    0,
    "",
    "",
    "",
    "",
    listOf(),
    0
)

class FakeWeatherRepo : WeatherRepository {
    override suspend fun getForecast(
        coordinates: CityGeometry,
        cityName: String,
        unit: TempUnit
    ): Weather {
        return fakeWeather
    }

    override suspend fun getWeather(id: String): Flow<Weather> {
        return flowOf(fakeWeather)
    }

    override suspend fun removeWeather(id: String): Weather {
        return fakeWeather
    }

    override suspend fun getAlCitiesShortForecast(): Flow<List<WeatherShort>> {
        return flowOf(
            listOf(
                WeatherShort(
                    "",
                    "",
                    0,
                    0,
                    0f,
                    0f,
                    "",
                    0,
                    0,
                )
            )
        )
    }

    override suspend fun saveWeather(weather: Weather) {
    }

    override suspend fun updateWeathers(unit: TempUnit) {
    }
}