package com.example.weather.presentation.screens.addCity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.models.City
import com.example.weather.domain.useCases.city.GetCityUseCase
import com.example.weather.domain.useCases.settings.GetSettingsUseCase
import com.example.weather.domain.useCases.weather.SaveCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCityViewModel @Inject constructor(
    private val getCityUseCase: GetCityUseCase,
    private val getSettings: GetSettingsUseCase,
    private val saveCityUseCase: SaveCityUseCase,
) : ViewModel() {

    private var job: Job? = null
    var savingCity by mutableStateOf(false)
        private set

    var predictions by mutableStateOf(listOf<City>())
        private set

    private fun getPredictions(address: String): Job {
        return viewModelScope.launch {
            delay(500)
            predictions = getCityUseCase(input = address)
        }
    }

    fun clear() {
        predictions = listOf()
        savingCity = false
    }

    fun onSearchChange(name: String) {
        job?.cancel()
        job = getPredictions(name)
    }

    fun onCitySelect(city: City, onFinish: () -> Unit) {
        savingCity = true
        viewModelScope.launch {
            saveCityUseCase(city, getSettings().first().tempUnit)
            onFinish()
        }
    }
}