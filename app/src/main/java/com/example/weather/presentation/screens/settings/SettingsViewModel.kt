package com.example.weather.presentation.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.models.Settings
import com.example.weather.domain.models.TempUnit
import com.example.weather.domain.useCases.settings.GetSettingsUseCase
import com.example.weather.domain.useCases.settings.UpdateCurrentTempUnitUseCase
import com.example.weather.domain.useCases.weather.UpdateWeatherDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getSettings: GetSettingsUseCase,
    private val updateCurrentTempUnit: UpdateCurrentTempUnitUseCase,
    private val updateWeatherDataUseCase: UpdateWeatherDataUseCase
) :
    ViewModel() {

    lateinit var settings: StateFlow<Settings>
        private set


    init {
        getSetting()
    }

    private fun getSetting() {
        viewModelScope.launch {
            settings = getSettings().map {
                it
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = Settings()
            )
        }
    }

    fun setTempUnit(unit: TempUnit) {
        viewModelScope.launch {
            updateCurrentTempUnit(unit)
            updateWeatherDataUseCase(unit)
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}