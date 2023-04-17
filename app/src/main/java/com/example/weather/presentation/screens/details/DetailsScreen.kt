package com.example.weather.presentation.screens.details

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailsScreen() {
    val detailsViewModel = hiltViewModel<DetailsViewModel>()
    val uiState = detailsViewModel.uiState

    val weatherDetails = uiState.details

    if (weatherDetails !== null) {
        TabComponent(weatherDetails)
    }
}