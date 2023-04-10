package com.example.weather.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.useCases.city.GetAllCityWeatherUseCase
import com.example.weather.domain.useCases.settings.GetSettingsUseCase
import com.example.weather.domain.useCases.weather.RemoveWeatherUseCase
import com.example.weather.domain.useCases.Undoable
import com.example.weather.domain.useCases.weather.UpdateWeatherDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Stack
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllForecastUseCase: GetAllCityWeatherUseCase,
    private val updateWeatherDataUseCase: UpdateWeatherDataUseCase,
    private val removeWeatherUseCase: RemoveWeatherUseCase,
    private val getSettings: GetSettingsUseCase
) : ViewModel() {

    private val _weatherRemovalStack = MutableStateFlow<Stack<Undoable>>(Stack())
    val weatherRemovalStack: StateFlow<Stack<Undoable>> = _weatherRemovalStack.asStateFlow()

    private val _isUpdating = MutableStateFlow(false)
    val isUpdating: StateFlow<Boolean> = _isUpdating.asStateFlow()

    lateinit var homeUiState: StateFlow<HomeUiState>
        private set

    var cancelStackClearJob: Job? = null

    init {
        getCitiesWeatherInfo()
    }

    private fun getCitiesWeatherInfo() {
        viewModelScope.launch {
            homeUiState = getAllForecastUseCase().map {
                HomeUiState(weathers = it)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState(weathers = listOf())
            )
        }
    }

    fun updateWeathers() {
        _isUpdating.update { true }
        viewModelScope.launch {
            updateWeatherDataUseCase(getSettings().first().tempUnit)
        }.invokeOnCompletion {
            _isUpdating.update { false }
        }
    }

    fun removeWeather(id: String) {
        viewModelScope.launch {
            _weatherRemovalStack.update { current ->
                current.push(removeWeatherUseCase(id))
                current
            }
            scheduleClearStackJob()
        }
    }

    private fun scheduleClearStackJob() {
        cancelStackClearJob?.cancel()
        cancelStackClearJob = viewModelScope.launch {
            delay(5000)
            _weatherRemovalStack.update { Stack() }
        }
    }

    fun undo() {
        viewModelScope.launch {
            _weatherRemovalStack.value.pop().undo()
            scheduleClearStackJob()
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}