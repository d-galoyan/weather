package com.example.weather.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material.MaterialTheme
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import com.example.weather.presentation.utils.DateTimeUtils
import java.time.LocalDateTime


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navigateToDetails: (String) -> Unit,
    onNewCityClick: () -> Unit,
) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val uiState by homeViewModel.homeUiState.collectAsState()
    val weathers = uiState.weathers
    val isRefreshing = homeViewModel.isUpdating
    val weatherRemovalStack = homeViewModel.weatherRemovalStack
    val pullRefreshState =
        rememberPullRefreshState(isRefreshing, { homeViewModel.updateWeathers() })

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
                    weathers = weathers,
                    navigateToDetails = navigateToDetails,
                    removeWeather = { homeViewModel.removeWeather(it) }
                )
            }
        }

        AddButton(
            size = weatherRemovalStack.size,
            onAddCity = onNewCityClick,
            modifier = Modifier.align(alignment = Alignment.BottomEnd),
        )

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )

        UndoRemoval(
            size = weatherRemovalStack.size,
            undo = { homeViewModel.undo() },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}