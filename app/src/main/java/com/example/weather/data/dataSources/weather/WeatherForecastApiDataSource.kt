package com.example.weather.data.dataSources.weather

import com.example.weather.data.mappers.toWeather
import com.example.weather.data.utils.DateFormatter
import com.example.weather.domain.dataSources.WeatherForecastDataSource
import com.example.weather.domain.models.City
import com.example.weather.domain.models.CityGeometry
import com.example.weather.domain.models.TempUnit
import com.example.weather.domain.models.Weather
import javax.inject.Inject

class WeatherForecastApiDataSource @Inject constructor(private val weatherApi: WeatherApiService) :
    WeatherForecastDataSource {
    override suspend fun getForecast(
        coordinates: CityGeometry,
        city: City,
        unit: TempUnit
    ): Weather {
        val (long, lat) = coordinates

        val dateFormatter = DateFormatter()

        return weatherApi.getWeatherInfo(
            long,
            lat,
            dateFormatter.getCurrentFormattedDate(),
            dateFormatter.addDays(9).getFormattedDate(),
            unit.value
        ).toWeather(city)
    }
}