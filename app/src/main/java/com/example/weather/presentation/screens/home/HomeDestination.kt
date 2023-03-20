package com.example.weather.presentation.screens.home

import com.example.weather.R
import com.example.weather.presentation.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route: String = "home"
    override val titleRes: Int = R.string.cities
}