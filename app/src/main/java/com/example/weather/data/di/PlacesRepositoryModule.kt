package com.example.weather.data.di

import com.example.weather.data.dataSourceImpls.places.GooglePlacesDataSource
import com.example.weather.data.repos.PlacesRepo
import com.example.weather.domain.repositories.PlacesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class PlacesRepositoryModule {

    @Provides
    fun providesPlacesRepository(googlePlacesDataSource: GooglePlacesDataSource): PlacesRepository {
        return PlacesRepo(googlePlacesDataSource)
    }
}