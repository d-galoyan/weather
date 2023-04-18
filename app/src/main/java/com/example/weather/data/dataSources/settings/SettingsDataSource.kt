package com.example.weather.data.dataSources.settings

import com.example.weather.data.db.settings.SettingsDao
import com.example.weather.data.mappers.toSettings
import com.example.weather.data.mappers.toSettingsEntity
import com.example.weather.domain.dataSources.SettingDataSource
import com.example.weather.domain.models.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsDataSourceImpl @Inject constructor(private val settingsDao: SettingsDao) :
    SettingDataSource {
    override suspend fun getSettings(): Flow<Settings> {
        return settingsDao.getSettings().map { it?.toSettings() ?: Settings()}
    }

    override suspend fun updateSettings(settings: Settings) {
        return settingsDao.insert(settings.toSettingsEntity())
    }
}