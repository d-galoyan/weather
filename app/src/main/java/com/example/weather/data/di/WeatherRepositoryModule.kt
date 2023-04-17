package com.example.weather.data.di

import com.example.weather.data.dataSources.weather.WeatherForecastApiDataSource
import com.example.weather.data.dataSources.weather.WeatherLocalDataSource
import com.example.weather.data.repos.WeatherRepo
import com.example.weather.domain.repositories.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class WeatherRepositoryModule {

    @Provides
    fun providesWeatherRepository(
        weatherLocalDataSource: WeatherLocalDataSource,
        weatherApiService: WeatherForecastApiDataSource
    ): WeatherRepository {
        return WeatherRepo(weatherLocalDataSource, weatherApiService)
    }
}


