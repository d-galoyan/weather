package com.example.weather.data.dataSourceImpls.settings

import android.content.Context
import androidx.datastore.core.DataStore
import com.example.weather.domain.models.Settings
import com.example.weather.settingsDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DataStoreModule {

    @Provides
    fun provideDataStore(@ApplicationContext appContext: Context): DataStore<Settings> {
        return appContext.settingsDataStore
    }
}