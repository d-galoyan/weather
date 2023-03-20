package com.example.weather.data.dataSourceImpls.settings

import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import com.example.weather.domain.dataSources.SettingDataSource
import com.example.weather.domain.models.Settings
import com.example.weather.domain.models.TempUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

object SettingsSerializer : Serializer<Settings> {
    override val defaultValue: Settings = Settings()

    override suspend fun readFrom(input: InputStream): Settings {
        return try {
            Json.decodeFromString(
                deserializer = Settings.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(
        t: Settings,
        output: OutputStream
    ) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(serializer = Settings.serializer(), value = t)
                    .encodeToByteArray()
            )
        }
    }
}

class SettingsDataSource @Inject constructor(private val dataStore: DataStore<Settings>) :
    SettingDataSource {
    override suspend fun getSettings(): Flow<Settings> {
        return dataStore.data
    }

    override suspend fun setTempUnit(unit: TempUnit) {
        dataStore.updateData { settings ->
            settings.copy(tempUnit = unit)
        }
    }
}