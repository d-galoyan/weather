package com.example.weather.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import okhttp3.MediaType.Companion.toMediaType
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule @Inject constructor() {

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://placeholder.com/")
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()
    }

    companion object {
        val json = Json {
            ignoreUnknownKeys = true
        }
    }

}