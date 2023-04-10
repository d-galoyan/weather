package com.example.weather.presentation.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.weather.R
import com.example.weather.domain.useCases.Undoable
import java.util.Stack

@Composable
fun UndoRemoval(
    undoable: Stack<Undoable>,
    undo: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = "${stringResource(R.string.city_forecast_deleted)} (${undoable.size})")
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