package com.example.weather.data.di

import com.example.weather.data.dataSourceImpls.weather.WeatherLocalDataSource
import com.example.weather.data.repos.WeatherRepo
import com.example.weather.domain.repositories.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class WeatherRepositoryModule {

    @Provides
    fun providesWeatherRepository(weatherLocalDataSource: WeatherLocalDataSource): WeatherRepository {
        return WeatherRepo(weatherLocalDataSource)
    }
}


