package com.example.weather.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material.MaterialTheme
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import com.example.weather.data.utils.ConnectionState
import com.example.weather.data.utils.DateTimeUtils
import com.example.weather.presentation.utils.connectivityState
import java.time.LocalDateTime

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navigateToDetails: (String) -> Unit,
    onNewCityClick: () -> Unit,
) {
    val connection by connectivityState()
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val uiState by homeViewModel.homeUiState.collectAsState()
    val weathers = uiState.weathers
    val isRefreshing by homeViewModel.isUpdating.collectAsState()
    val pullRefreshState =
        rememberPullRefreshState(isRefreshing, { homeViewModel.updateWeathers() })

    val isConnected = connection === ConnectionState.Available

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
            .pullRefresh(pullRefreshState)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            if (weathers.isNotEmpty()) {
                Text(
                    text = DateTimeUtils(LocalDateTime.now().toString()).getDateTime(),
                    modifier = Modifier.padding(bottom = 16.dp),
                )
                CitiesGridScreen(
                    weathers,
                    navigateToDetails,
                    removeWeather = { homeViewModel.removeWeather(it) })
            }
        }

        FloatingActionButton(
            onClick = {
                if (isConnected) onNewCityClick()
            },
            backgroundColor = if (isConnected) MaterialTheme.colors.onPrimary else Color.Gray,
            modifier = Modifier
                .padding(all = 32.dp)
                .align(alignment = Alignment.BottomEnd),
        ) {
            Icon(Icons.Filled.Add, "Add New City")
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}