package com.example.weather.data.dataSourceImpls.places

import com.example.weather.data.di.NetworkModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import javax.inject.Inject

@Module(includes = [NetworkModule::class])
@InstallIn(ViewModelComponent::class)
class GooglePlacesApiServiceModule @Inject constructor() {

    @Provides
    fun providesGooglePlacesApiService(retrofit: Retrofit): GooglePlacesApiService {
        return retrofit.newBuilder().baseUrl(GooglePlacesApiService.BASE_URL).build()
            .create(GooglePlacesApiService::class.java)
    }
}