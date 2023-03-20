package com.example.weather;

import android.app.Application;
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.weather.data.dataSourceImpls.settings.SettingsSerializer
import com.example.weather.data.workers.updateWeatherWorker
import com.example.weather.domain.models.Settings
import dagger.hilt.android.HiltAndroidApp;

val Context.settingsDataStore: DataStore<Settings> by dataStore(
    fileName = "settings.json",
    serializer = SettingsSerializer
)

@HiltAndroidApp
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        updateWeatherWorker(this)
    }
}