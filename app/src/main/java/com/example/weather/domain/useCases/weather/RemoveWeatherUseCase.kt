package com.example.weather.domain.useCases.weather

import com.example.weather.domain.repositories.WeatherRepository
import com.example.weather.domain.useCases.Undoable
import javax.inject.Inject

class RemoveWeatherUseCase @Inject constructor(
    private val weatherRepo: WeatherRepository,
) {

    suspend operator fun invoke(id : String) : Undoable {
        val weather = weatherRepo.removeWeather(id)
        return object : Undoable {
            override suspend fun undo() {
                weatherRepo.saveWeather(weather)
            }
        }
    }
}