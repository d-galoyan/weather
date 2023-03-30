package com.example.weather.data.dataSourceImpls.weather

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET(
        "forecast?hourly=weathercode,relativehumidity_2m,pressure_msl,temperature_2m," +
                "apparent_temperature&current_weather=true&timezone=auto&daily=temperature_2m_max," +
                "temperature_2m_min,sunrise,sunset" +
                ",windspeed_10m_max,weathercode"
    )
    suspend fun getWeatherInfo(
        @Query("longitude") long: Float,
        @Query("latitude") lat: Float,
        @Query("start_date") start: String,
        @Query("end_date") end: String,
        @Query("temperature_unit") unit: String,
    ): WeatherDataModel

    companion object {
        const val BASE_URL = "https://api.open-meteo.com/v1/"
    }
}