package com.example.weather.domain.repositories

import com.example.weather.domain.models.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun getSettings() : Flow<Settings>
    suspend fun updateSettings(settings: Settings)
}