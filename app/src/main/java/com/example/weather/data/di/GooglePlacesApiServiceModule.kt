package com.example.weather.data.di

import com.example.weather.data.dataSources.places.GooglePlacesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
@InstallIn(ViewModelComponent::class)
class GooglePlacesApiServiceModule {

    @Provides
    fun providesGooglePlacesApiService(retrofitBuilder: Retrofit.Builder): GooglePlacesApiService {
        return retrofitBuilder.baseUrl(GooglePlacesApiService.BASE_URL).build()
            .create(GooglePlacesApiService::class.java)
    }
}