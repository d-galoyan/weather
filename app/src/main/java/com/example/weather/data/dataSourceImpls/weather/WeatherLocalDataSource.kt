package com.example.weather.data.dataSourceImpls.weather

import androidx.room.Transaction
import androidx.room.withTransaction
import com.example.weather.data.db.WeatherDatabase
import com.example.weather.data.utils.DateFormatter
import com.example.weather.domain.dataSources.WeatherDataSource
import com.example.weather.domain.models.CityGeometry
import com.example.weather.domain.models.TempUnit
import com.example.weather.domain.models.Weather
import com.example.weather.domain.models.WeatherShort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherLocalDataSource @Inject constructor(
    private val database: WeatherDatabase,
    private val weatherApi: WeatherApiService
) : WeatherDataSource {

    override suspend fun getForecast(
        coordinates: CityGeometry,
        cityName: String,
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
        ).toWeather(cityName)
    }

    override suspend fun getWeather(id: String): Flow<Weather> {
        return database.weatherDao().getWeather(id).map { it.toWeather() }
    }

    override suspend fun removeWeather(id: String): Weather {
        val weather = getWeather(id).first()
        database.weatherDao().deleteWeather(id)
        return weather
    }

    override suspend fun getAlCitiesShortForecast(): Flow<List<WeatherShort>> {
        return database.weatherDao().getAllCitiesWeather()
            .map { it -> it.map { it.toShortWeather() } }
    }

    @Transaction
    override suspend fun saveWeather(weather: Weather) {
        database.withTransaction {
            database.weatherDao().insert(weather.toWeatherEntity())
            val ids = database.weatherDao()
                .insertDaily(weather.days.map { it.toWeatherDailyEntity(weather.id) })

            val hourlies = ids.mapIndexed { index, id ->
                weather.days[index].hourly.map {
                    it.toWeatherHourlyEntity(id)
                }
            }

            database.weatherDao().insertHourly(hourlies.flatten())
        }
    }

    @Transaction
    override suspend fun updateWeathers(unit: TempUnit) {
        database.withTransaction {
            val weatherShort = getAlCitiesShortForecast().first()

            weatherShort.forEach {
                val weather = getForecast(
                    CityGeometry(
                        lng = it.long,
                        lat = it.lat,
                    ),
                    it.cityName,
                    unit = unit
                )
                saveWeather(weather = weather.copy(id = it.id))
            }
        }
    }
}