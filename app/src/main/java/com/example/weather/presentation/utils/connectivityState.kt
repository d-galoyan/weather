package com.example.weather.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import com.example.weather.data.utils.ConnectionState
import com.example.weather.data.utils.currentConnectivityState
import com.example.weather.data.utils.observeConnectivityAsFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun connectivityState(): State<ConnectionState> {
    val context = LocalContext.current

    return produceState(initialValue = context.currentConnectivityState) {
        context.observeConnectivityAsFlow().collect { value = it }
    }
}