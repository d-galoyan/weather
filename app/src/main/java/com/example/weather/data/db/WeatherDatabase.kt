package com.example.weather.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.weather.data.dataSourceImpls.settings.toSettingsEntity
import com.example.weather.data.db.settings.SettingsDao
import com.example.weather.data.db.settings.SettingsEntity
import com.example.weather.data.db.weather.WeatherDao
import com.example.weather.domain.models.Settings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        WeatherEntity::class,
        HourlyWeatherEntity::class,
        DailyWeatherEntity::class,
        SettingsEntity::class
    ],
    version = 15,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
    abstract fun settingsDao(): SettingsDao

    companion object {
        @Volatile
        private var Instance: WeatherDatabase? = null

        fun getDatabase(context: Context): WeatherDatabase {
            return Instance ?: synchronized(this) {
                Room
                    .databaseBuilder(context, WeatherDatabase::class.java, "weather_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(RoomDatabaseCallbacks)
                    .build()
                    .also { Instance = it }
            }
        }

        private val RoomDatabaseCallbacks = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(Dispatchers.IO).launch {
                    Instance?.settingsDao()?.insert(Settings().toSettingsEntity())
                }
            }
        }
    }
}