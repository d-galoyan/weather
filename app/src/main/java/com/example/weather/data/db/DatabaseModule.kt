package com.example.weather.data.db

import android.content.Context
import com.example.weather.data.db.settings.SettingsDao
import com.example.weather.data.db.weather.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideWeatherDao(appDatabase: WeatherDatabase): WeatherDao {
        return appDatabase.weatherDao()
    }

    @Provides
    fun provideSettingsDao(appDatabase: WeatherDatabase): SettingsDao {
        return appDatabase.settingsDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): WeatherDatabase {
        return WeatherDatabase.getDatabase(appContext)
    }
}