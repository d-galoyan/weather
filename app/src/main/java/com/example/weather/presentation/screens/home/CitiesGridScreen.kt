package com.example.weather.presentation.screens.home

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weather.domain.models.WeatherShort
import com.example.weather.presentation.commons.DayNightTempInfo
import com.example.weather.presentation.commons.ImageByWeatherCode

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CitiesGridScreen(
    weathers: List<WeatherShort>,
    navigateToDetails: (String) -> Unit,
    removeWeather: (String) -> Unit,
) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        items(weathers.size, key = { weathers[it].id }) { index ->
            val weather = weathers[index]
            Card(
                elevation = 2.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .animateItemPlacement(
                        animationSpec = spring(
                            stiffness = Spring.StiffnessLow,
                        )
                    )
                    .clickable {
                        navigateToDetails(weather.id)
                    }
            ) {
                SwipeToAction(action = { removeWeather(weather.id) }) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(end = 16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                                .height(IntrinsicSize.Max)
                        ) {
                            Text(
                                text = weather.cityName,
                                style = MaterialTheme.typography.h5,
                            )
                            DayNightTempInfo(weather)
                        }
                        ImageByWeatherCode(
                            weather.weatherCode, Modifier.size(128.dp)
                        )
                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeToAction(action: () -> Unit, item: @Composable () -> Unit) {
    val dismissState = rememberDismissState(
        confirmStateChange = {
            if (it.name == "DismissedToStart") {
                action()
            }
            true
        }
    )

    SwipeToDismiss(
        state = dismissState,
        directions = setOf(
            DismissDirection.EndToStart,
        ),
        dismissThresholds = { FractionalThreshold(0.7f) },
        background = {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.error)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    Icons.Default.Delete,
                    tint = Color.White,
                    contentDescription = null,
                )
            }
        },
        dismissContent = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterVertically),
                content = item
            )
        }
    )
}