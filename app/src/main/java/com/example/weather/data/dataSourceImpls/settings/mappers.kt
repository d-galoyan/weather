package com.example.weather.data.dataSourceImpls.settings

import com.example.weather.data.db.settings.SettingsEntity
import com.example.weather.domain.models.Settings

fun SettingsEntity.toSettings() : Settings {
    return  Settings(
        id = id,
        tempUnit = tempUnit
    )
}

fun Settings.toSettingsEntity() : SettingsEntity {
    return  SettingsEntity(
        id = id,
        tempUnit = tempUnit
    )
}