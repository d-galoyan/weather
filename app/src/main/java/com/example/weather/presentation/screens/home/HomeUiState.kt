package com.example.weather.presentation.screens.home

import com.example.weather.domain.models.WeatherShort

data class HomeUiState(
    val weathers : List<WeatherShort>,
)