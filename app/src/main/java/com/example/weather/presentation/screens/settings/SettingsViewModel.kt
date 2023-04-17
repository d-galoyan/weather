package com.example.weather.presentation.screens.settings

import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.models.Settings
import com.example.weather.domain.models.TempUnit
import com.example.weather.domain.models.ThemeMode
import com.example.weather.domain.useCases.settings.GetSettingsUseCase
import com.example.weather.domain.useCases.settings.UpdateSettingsUseCase
import com.example.weather.domain.useCases.weather.UpdateWeatherDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getSettings: GetSettingsUseCase,
    private val updateSettings: UpdateSettingsUseCase,
    private val updateWeatherDataUseCase: UpdateWeatherDataUseCase
) : ViewModel() {

    lateinit var settings: StateFlow<Settings>
        private set

    val isDark: Boolean
        get() = settings.value.theme == ThemeMode.Dark

    init {
        getSetting()
    }

    private fun getSetting() {
        viewModelScope.launch {
            settings = getSettings().map { it }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = Settings()
            )
        }
    }

    fun setTempUnit(unit: TempUnit) {
        viewModelScope.launch {
            updateSettings(
                settings.value.copy(tempUnit = unit)
            )
            updateWeatherDataUseCase(unit)
        }
    }

    fun setThemeMode(theme: ThemeMode) {
        viewModelScope.launch {
            updateSettings(
                settings.value.copy(theme = theme)
            )
        }
    }
}