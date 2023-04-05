package com.example.weather.data.dataSourceImpls.settings

import com.example.weather.data.db.WeatherDatabase
import com.example.weather.domain.dataSources.SettingDataSource
import com.example.weather.domain.models.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class SettingsDataSourceImpl @Inject constructor(private val db: WeatherDatabase) :
    SettingDataSource {
    override suspend fun getSettings(): Flow<Settings> {
        return db.settingsDao().getSettings().map { it?.toSettings() ?: Settings()}
    }

    override suspend fun updateSettings(settings: Settings) {
        return db.settingsDao().update(settings.toSettingsEntity())
    }
}