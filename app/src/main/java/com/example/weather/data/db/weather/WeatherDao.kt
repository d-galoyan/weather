package com.example.weather.data.db.weather

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT MAX(position) FROM weather")
    fun getTheLastPosition(): Int?

    @Query("SELECT * from weather ORDER BY position ASC")
    fun getAllCitiesWeather(): Flow<List<WeatherEntity>>

    @Transaction
    @Query("SELECT * from weather WHERE id = :id")
    suspend fun getWeather(id: String): WeatherWithHourlyAndDaily

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weather: WeatherEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHourly(hourly: List<HourlyWeatherEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDaily(daily: List<DailyWeatherEntity>): List<Long>


    @Query("DELETE from weather WHERE id=:id")
    suspend fun deleteWeather(id: String)
}
