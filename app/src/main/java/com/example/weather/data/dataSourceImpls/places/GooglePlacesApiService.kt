package com.example.weather.data.dataSourceImpls.places

import retrofit2.http.GET
import retrofit2.http.Query

const val GOOGLE_API_KEY = "AIzaSyDYOfOwM5WG_ODXJC_hPMxWEE2rmHD4W5M"


interface GooglePlacesApiService {

    @GET("autocomplete/json")
    suspend fun getPredictions(
        @Query("key") key: String = GOOGLE_API_KEY,
        @Query("types") types: String = "locality",
        @Query("input") input: String
    ): GooglePredictionDataModel

    @GET("details/json")
    suspend fun getGeometry(
        @Query("key") key: String = GOOGLE_API_KEY,
        @Query("placeid") placeId: String,
        @Query("fields") fields: String = "geometry"
    ): PlaceGeometry

    companion object {
        const val BASE_URL = "https://maps.googleapis.com/maps/api/place/"
    }
}