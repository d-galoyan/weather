package com.example.weather.data.di

import com.example.weather.data.dataSourceImpls.settings.SettingsDataSource
import com.example.weather.data.repos.SettingsRepo
import com.example.weather.domain.repositories.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class SettingsRepositoryModule {

    @Provides
    fun providesSettingsRepository(settingsDataSource: SettingsDataSource): SettingsRepository {
        return SettingsRepo(settingsDataSource)
    }
}