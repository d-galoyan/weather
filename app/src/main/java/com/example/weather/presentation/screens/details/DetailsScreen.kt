package com.example.weather.presentation.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailsScreen() {
    val detailsViewModel = hiltViewModel<DetailsViewModel>()
    val uiState by detailsViewModel.uiState.collectAsState()

    val weatherDetails = uiState.details

    if (weatherDetails !== null) {
        TabComponent(weatherDetails)
    }
}