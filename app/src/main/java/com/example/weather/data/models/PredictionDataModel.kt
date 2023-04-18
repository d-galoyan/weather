package com.example.weather.data.models

import com.google.gson.annotations.SerializedName

data class PredictionDataModel(
    val predictions: List<Prediction>
)

data class Prediction(
    val description: String,
    @SerializedName(value = "place_id")
    val placeId: String
)

data class PlaceGeometry(
    val result: PlaceResult
)

data class PlaceResult(
    val geometry: Geometry
)

data class Geometry(
    val location: Location
)

data class Location(
    val lat: Float,
    val lng: Float
)