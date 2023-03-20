package com.example.weather.presentation.screens.settings

import com.example.weather.R
import com.example.weather.presentation.navigation.NavigationDestination

object SettingsDestination: NavigationDestination {
    override val route: String = "settings"
    override val titleRes: Int = R.string.settings
}