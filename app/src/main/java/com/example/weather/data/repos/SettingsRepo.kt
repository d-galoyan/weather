package com.example.weather.data.repos

import com.example.weather.domain.dataSources.SettingDataSource
import com.example.weather.domain.models.Settings
import com.example.weather.domain.models.TempUnit
import com.example.weather.domain.repositories.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepo @Inject constructor(private val settingsDataStore : SettingDataSource) : SettingsRepository {
    override suspend fun getSettings(): Flow<Settings> {
        return settingsDataStore.getSettings()
    }

    override suspend fun setTempUnit(unit: TempUnit) {
        settingsDataStore.setTempUnit(unit)
    }
}