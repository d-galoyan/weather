package com.example.weather.domain.useCases.settings

import com.example.weather.domain.models.Settings
import com.example.weather.domain.repositories.SettingsRepository
import javax.inject.Inject

class UpdateSettingsUseCase  @Inject constructor(private val settingsRepo: SettingsRepository) {

    suspend operator fun invoke(settings : Settings){
        settingsRepo.updateSettings(settings)
    }
}