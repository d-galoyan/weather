package com.example.weather.presentation.screens.home

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weather.presentation.utils.ConnectionState
import com.example.weather.presentation.utils.connectivityState

@Composable
fun AddButton(size : Int, onAddCity : () -> Unit, modifier: Modifier) {

    val connection by connectivityState()

    val isConnected = connection === ConnectionState.Available

    var addButtonBottomOffset by remember { mutableStateOf(16.dp) }

    val addButtonAnimation by animateIntAsState(
        targetValue = if (size > 0) 64 else 16,
        animationSpec = tween(durationMillis = 500)
    )

    addButtonBottomOffset = addButtonAnimation.dp

    FloatingActionButton(
        onClick = {
            if (isConnected) onAddCity()
        },
        backgroundColor = if (isConnected) MaterialTheme.colors.onPrimary else Color.Gray,
        modifier = modifier
            .padding(end = 24.dp, bottom = addButtonBottomOffset)

    ) {
        Icon(Icons.Filled.Add, null)
    }
}