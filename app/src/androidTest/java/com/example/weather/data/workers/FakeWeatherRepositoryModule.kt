package com.example.weather.data.workers

import com.example.weather.data.di.WeatherRepositoryModule
import com.example.weather.data.repos.FakeWeatherRepo
import com.example.weather.domain.repositories.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [WeatherRepositoryModule::class]
)
@Module
class FakeWeatherRepositoryModule {

    @Provides
    fun providesFakeWeatherRepository(): WeatherRepository {
        return FakeWeatherRepo()
    }
}


