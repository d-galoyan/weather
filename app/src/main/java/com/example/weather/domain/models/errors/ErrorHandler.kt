package com.example.weather.domain.models.errors

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorEntity
}