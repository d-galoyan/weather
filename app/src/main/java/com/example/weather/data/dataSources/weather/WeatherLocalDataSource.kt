package com.example.weather.data.dataSources.weather

import androidx.room.Transaction
import androidx.room.withTransaction
import com.example.weather.data.db.WeatherDatabase
import com.example.weather.data.mappers.toShortWeather
import com.example.weather.data.mappers.toWeather
import com.example.weather.data.mappers.toWeatherDailyEntity
import com.example.weather.data.mappers.toWeatherEntity
import com.example.weather.data.mappers.toWeatherHourlyEntity
import com.example.weather.domain.dataSources.WeatherDataSource
import com.example.weather.domain.models.Weather
import com.example.weather.domain.models.WeatherShort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherLocalDataSource @Inject constructor(
    private val db: WeatherDatabase,
) : WeatherDataSource {

    override suspend fun <R> withTransaction(block: suspend () -> R): R {
        return db.withTransaction {
            block()
        }
    }

    override suspend fun getWeather(id: String): Flow<Weather> {
        return db.weatherDao().getWeather(id).map { it.toWeather() }
    }

    override suspend fun removeWeather(id: String): Weather {
        val weather = getWeather(id).first()
        db.weatherDao().deleteWeather(id)
        return weather
    }

    override suspend fun getWeathersShort(): Flow<List<WeatherShort>> {
        return db.weatherDao().getAllCitiesWeather()
            .map { it -> it.map { it.toShortWeather() } }
    }


    private fun getThePosition(initialPosition: Int): Int {
        if (initialPosition != 0) {
            return initialPosition
        }
        val lastPosition = db.weatherDao().getTheLastPosition()


        return if (lastPosition != null) lastPosition + 1 else 1
    }

    @Transaction
    override suspend fun saveWeather(weather: Weather) {
        db.withTransaction {
            db.weatherDao().insert(weather.toWeatherEntity(getThePosition(weather.position)))
            val ids = db.weatherDao()
                .insertDaily(weather.days.map { it.toWeatherDailyEntity(weather.id) })

            val hourlies = ids.mapIndexed { index, id ->
                weather.days[index].hourly.map {
                    it.toWeatherHourlyEntity(id)
                }
            }

            db.weatherDao().insertHourly(hourlies.flatten())
        }
    }
}