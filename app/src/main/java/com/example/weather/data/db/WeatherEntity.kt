package com.example.weather.data.db

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val cityName: String,
    val lat: Float,
    val lng: Float,
    val dailyMax: Int,
    val nightMax: Int,
    val currentTemp: Float,
    val windSpeed: Float,
    val tempUnit: String,
    val humidityUnit : String,
    val pressureUnit : String,
    val windSpeedUnit: String,
    val weatherCode: Int,
)

@Entity(
    tableName = "daily", foreignKeys = [ForeignKey(
        entity = WeatherEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("weather_id"),
        onDelete = CASCADE
    )]
)
data class DailyWeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "weather_id", index = true)
    val weather_id: Long,
    val day: String,
    val maxTemp: Int,
    val minTemp: Int,
    val sunrise: String,
    val sunset: String,
    val windSpeed: Float,
    val weatherCode: Int,
)

@Entity(
    tableName = "hourly", foreignKeys = [ForeignKey(
        entity = DailyWeatherEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("day_id"),
        onDelete = CASCADE
    )]
)
data class HourlyWeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "day_id", index = true)
    val day_id: Long,
    val time: String,
    val temp: Float,
    val apparentTemp: Float,
    val humidity: Int,
    val pressure: Float,
    val weatherCode: Int,
)

data class WeatherWithHourlyAndDaily(
    @Embedded
    val weather: WeatherEntity,
    @Relation(
        entity = DailyWeatherEntity::class,
        parentColumn = "id",
        entityColumn = "weather_id"
    )
    var daily: List<DailyWithHourly>
)


data class DailyWithHourly(
    @Embedded
    val daily: DailyWeatherEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "day_id",
    )
    var hourly: List<HourlyWeatherEntity>,
)