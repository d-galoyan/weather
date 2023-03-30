package com.example.weather

import android.app.Application
import com.example.weather.data.workers.updateWeatherWorker
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        updateWeatherWorker(this)
    }
}