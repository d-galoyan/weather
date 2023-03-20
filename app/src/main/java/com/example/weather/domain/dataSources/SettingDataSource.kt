package com.example.weather.domain.dataSources

import com.example.weather.domain.models.Settings
import com.example.weather.domain.models.TempUnit
import kotlinx.coroutines.flow.Flow

interface SettingDataSource {

    suspend fun getSettings(): Flow<Settings>
    suspend fun setTempUnit(unit: TempUnit)

}