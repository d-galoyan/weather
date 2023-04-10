package com.example.weather.domain.useCases

interface Undoable {
    suspend fun undo()
}