package com.example.weather.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weather.domain.models.ThemeMode
import com.example.weather.presentation.screens.settings.SettingsViewModel

@Composable
fun themeMode(): ThemeMode {
    val settingsViewModel: SettingsViewModel = hiltViewModel()
    val settings by settingsViewModel.settings.collectAsState()
    return settings.theme
}