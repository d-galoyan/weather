/*
 * Copyright (C) 2022 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.weather.data.db.weather

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.weather.data.db.DailyWeatherEntity
import com.example.weather.data.db.HourlyWeatherEntity
import com.example.weather.data.db.WeatherEntity
import com.example.weather.data.db.WeatherWithHourlyAndDaily
import kotlinx.coroutines.flow.Flow

/**
 * Database access object to access the Inventory database
 */
@Dao
interface WeatherDao {

    @Query("SELECT * from weather")
    fun getAllCitiesWeather(): Flow<List<WeatherEntity>>

    @Transaction
    @Query("SELECT * from weather WHERE id = :id")
    fun getWeather(id: String): Flow<WeatherWithHourlyAndDaily>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weather: WeatherEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHourly(hourly: List<HourlyWeatherEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDaily(daily: List<DailyWeatherEntity>): List<Long>

    @Update
    suspend fun update(item: WeatherEntity)

    @Query("DELETE from weather WHERE id=:id")
    suspend fun deleteWeather(id: String)
}
