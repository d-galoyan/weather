package com.example.weather.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.weather.presentation.screens.settings.SettingsDestination

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreenLayout(navController: NavHostController, HomeScreen: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(id = HomeDestination.titleRes))
                },
                backgroundColor = MaterialTheme.colors.onPrimary,
                actions = {
                    IconButton(onClick = { navController.navigate(SettingsDestination.route) }) {
                        Icon(Icons.Filled.Settings, null)
                    }
                })
        },
    ) {
        HomeScreen()
    }
}