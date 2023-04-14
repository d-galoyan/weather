package com.example.weather.domain.dataSources

import com.example.weather.domain.models.Settings
import kotlinx.coroutines.flow.Flow

interface SettingDataSource {
    suspend fun getSettings(): Flow<Settings>
    suspend fun updateSettings(settings: Settings)
}