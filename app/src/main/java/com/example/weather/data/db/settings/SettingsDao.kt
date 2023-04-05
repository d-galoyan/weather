package com.example.weather.data.db.settings

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {
    @Query("SELECT * from settings")
    fun getSettings(): Flow<SettingsEntity?>

    @Update
    suspend fun update(item: SettingsEntity)

    @Insert
    suspend fun insert(item: SettingsEntity)
}