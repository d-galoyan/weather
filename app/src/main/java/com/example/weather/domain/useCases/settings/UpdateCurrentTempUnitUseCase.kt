package com.example.weather.domain.useCases.settings

import com.example.weather.domain.models.TempUnit
import com.example.weather.domain.repositories.SettingsRepository
import javax.inject.Inject

class UpdateCurrentTempUnitUseCase  @Inject constructor(private val settingsRepo: SettingsRepository) {

    suspend operator fun invoke(unit : TempUnit){
        settingsRepo.setTempUnit(unit)
    }
}