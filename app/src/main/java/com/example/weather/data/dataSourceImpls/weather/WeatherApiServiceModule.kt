package com.example.weather.data.dataSourceImpls.weather

import com.example.weather.data.di.NetworkModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Inject

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class WeatherApiServiceModule @Inject constructor() {

    @Provides
    fun provideApiService(retrofit: Retrofit): WeatherApiService {
        return retrofit.newBuilder().baseUrl(WeatherApiService.BASE_URL).build()
            .create(WeatherApiService::class.java)
    }
}