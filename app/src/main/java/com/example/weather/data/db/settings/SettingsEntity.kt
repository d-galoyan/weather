package com.example.weather.data.db.settings

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weather.domain.models.TempUnit
import com.example.weather.domain.models.ThemeMode

@Entity(tableName = "settings")
data class SettingsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val tempUnit: TempUnit,
    val theme: ThemeMode,
)