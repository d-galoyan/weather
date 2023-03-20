package com.example.weather.data.dataSourceImpls.weather

import com.example.weather.data.di.NetworkModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import javax.inject.Inject

@Module(includes = [NetworkModule::class])
@InstallIn(ViewModelComponent::class)
class WeatherApiServiceModule @Inject constructor() {

    @Provides
    fun bindApiService(retrofit: Retrofit): WeatherApiService {
        return retrofit.newBuilder().baseUrl(WeatherApiService.BASE_URL).build()
            .create(WeatherApiService::class.java)
    }
}