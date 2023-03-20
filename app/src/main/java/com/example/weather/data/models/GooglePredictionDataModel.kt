package com.example.weather.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GooglePredictionDataModel(
    val predictions: ArrayList<GooglePrediction>
)

@Serializable
data class GooglePrediction(
    val description: String,
    @SerialName(value = "place_id")
    val placeId: String
)

@Serializable
data class PlaceGeometry(
    val result: PlaceResult
)

@Serializable
data class PlaceResult(
    val geometry: Geometry
)

@Serializable
data class Geometry(
    val location: Location
)

@Serializable
data class Location(
    val lat: Float,
    val lng: Float
)