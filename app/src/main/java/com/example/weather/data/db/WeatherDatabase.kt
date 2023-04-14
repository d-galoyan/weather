package com.example.weather.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weather.data.db.settings.SettingsDao
import com.example.weather.data.db.settings.SettingsEntity
import com.example.weather.data.db.weather.DailyWeatherEntity
import com.example.weather.data.db.weather.HourlyWeatherEntity
import com.example.weather.data.db.weather.WeatherDao
import com.example.weather.data.db.weather.WeatherEntity

@Database(
    entities = [
        WeatherEntity::class,
        HourlyWeatherEntity::class,
        DailyWeatherEntity::class,
        SettingsEntity::class
    ],
    version = 19,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
    abstract fun settingsDao(): SettingsDao
}