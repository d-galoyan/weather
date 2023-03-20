package com.example.weather.domain.useCases.settings

import com.example.weather.domain.models.Settings
import com.example.weather.domain.repositories.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(private val settingsRepo: SettingsRepository) {

    suspend operator fun invoke() : Flow<Settings> {
        return settingsRepo.getSettings()
    }
}