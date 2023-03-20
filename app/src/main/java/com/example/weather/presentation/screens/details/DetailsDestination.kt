package com.example.weather.presentation.screens.details

import com.example.weather.R
import com.example.weather.presentation.navigation.NavigationDestination

object DetailsDestination : NavigationDestination {
    override val route: String = "details"
    override val titleRes: Int = R.string.detailed
    const val detailsIdArg = "cityId"
    val routeWithArgs = "$route/{$detailsIdArg}"
}

const val DegreeSymbol = "\u00B0"
