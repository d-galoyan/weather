package com.example.weather.presentation.screens.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.weather.R

@Composable
fun UndoRemoval(
    size: Int,
    undo: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.onPrimary)
            .animateContentSize(
                animationSpec = spring(
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        if (size > 0) {
            Column {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = "${stringResource(R.string.city_forecast_deleted)} (${size})")
                    Text(
                        text = stringResource(R.string.undo),
                        style = TextStyle(
                            textDecoration = TextDecoration.Underline,
                        ),
                        modifier = Modifier
                            .clickable { undo() }
                    )
                }
            }
        }
    }
}