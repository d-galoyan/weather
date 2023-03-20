package com.example.weather.presentation.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun Spinner(spin: Boolean, modifier: Modifier) {

    val strokeWidth = 5.dp

    if (spin) {
        CircularProgressIndicator(
            modifier = modifier.drawBehind {
                drawCircle(
                    Color.Gray,
                    radius = size.width / 2 - strokeWidth.toPx() / 2,
                    style = Stroke(strokeWidth.toPx())
                )
            },
            color = Color.LightGray,
            strokeWidth = strokeWidth
        )
    }
}