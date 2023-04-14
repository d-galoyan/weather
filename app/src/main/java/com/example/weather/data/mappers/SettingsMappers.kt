package com.example.weather.data.mappers

import com.example.weather.data.db.settings.SettingsEntity
import com.example.weather.domain.models.Settings

fun SettingsEntity.toSettings() : Settings {
    return  Settings(
        id = id,
        tempUnit = tempUnit,
        theme = theme
    )
}

fun Settings.toSettingsEntity() : SettingsEntity {
    return  SettingsEntity(
        id = id,
        tempUnit = tempUnit,
        theme = theme
    )
}