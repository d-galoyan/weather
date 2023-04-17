package com.example.weather.presentation.screens.home

import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.example.weather.domain.useCases.Undoable
import com.example.weather.domain.useCases.city.*
import com.example.weather.domain.useCases.settings.*
import com.example.weather.domain.useCases.weather.*
import dagger.hilt.android.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllCityWeatherUseCase: GetAllCityWeatherUseCase,
    private val updateWeatherDataUseCase: UpdateWeatherDataUseCase,
    private val removeWeatherUseCase: RemoveWeatherUseCase,
    private val getSettingsUseCase: GetSettingsUseCase
) : ViewModel() {

    var weatherRemovalStack = mutableStateListOf<Undoable>()
        private set

    private val _homeUiState = MutableStateFlow(HomeUiState(weathers = emptyList()))
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    var isUpdating by mutableStateOf(false)
        private set

    private var clearStackJob: Job? = null

    init {
        getCitiesWeatherInfo()
    }

    private fun getCitiesWeatherInfo() {
        viewModelScope.launch {
            getAllCityWeatherUseCase().collect { weathers ->
                _homeUiState.value = HomeUiState(weathers = weathers)
            }
        }
    }

    fun updateWeathers() {
        isUpdating = true
        viewModelScope.launch {
            updateWeatherDataUseCase(getSettingsUseCase().first().tempUnit)
            isUpdating = false
        }
    }

    fun removeWeather(id: String) {
        viewModelScope.launch {
            weatherRemovalStack.add(removeWeatherUseCase(id))
            scheduleClearStackJob()
        }
    }

    private fun scheduleClearStackJob() {
        clearStackJob?.cancel()
        clearStackJob = viewModelScope.launch {
            delay(5000)
            weatherRemovalStack.clear()
        }
    }

    fun undo() {
        viewModelScope.launch {
            weatherRemovalStack.removeLastOrNull()?.undo()
            scheduleClearStackJob()
        }
    }
}
