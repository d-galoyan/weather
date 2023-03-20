package com.example.weather.presentation.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.useCases.weather.GetWeatherDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getWeatherDetailsUseCase: GetWeatherDetailsUseCase
) : ViewModel() {

    lateinit var uiState: StateFlow<DetailsUiState>
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[DetailsDestination.detailsIdArg])

    init {
        getWeatherDetails()
    }

    private fun getWeatherDetails() {
        viewModelScope.launch {
            uiState = getWeatherDetailsUseCase(itemId).map {
                DetailsUiState(details = it)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailsUiState()
            )
        }

    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}