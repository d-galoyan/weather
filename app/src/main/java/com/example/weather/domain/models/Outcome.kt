package com.example.weather.domain.models

import com.example.weather.domain.models.errors.ErrorEntity

sealed interface Outcome<T> {

    data class Success<T>(val data: T) : Outcome<T>

    data class Error<T>(val error: ErrorEntity) : Outcome<T>
}