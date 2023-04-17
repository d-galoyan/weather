package com.example.weather.presentation.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.useCases.weather.GetWeatherDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getWeatherDetailsUseCase: GetWeatherDetailsUseCase
) : ViewModel() {

    var uiState by mutableStateOf(DetailsUiState())
        private set

    private val itemId: String = checkNotNull(savedStateHandle[DetailsDestination.detailsIdArg])

    init {
        getWeatherDetails()
    }

    private fun getWeatherDetails() {
        viewModelScope.launch {
            val details = getWeatherDetailsUseCase(itemId)
            uiState = DetailsUiState(details = details)
        }
    }
}