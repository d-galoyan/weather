package com.example.weather.presentation.commons

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.ui.Alignment
import androidx.compose.material.*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.ui.unit.dp
import com.example.weather.domain.models.City

@Composable
fun AutoCompleteTextField(
    itemList: List<City>,
    onQuery: (String) -> Unit,
    onClearResults: () -> Unit,
    onItemSelect: (city: City) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String
) {

    var text by remember { mutableStateOf("") }

    LaunchedEffect(text) {
        if (text.isNotBlank()) {
            onQuery(text)
        }
    }

    Column {
        TextField(
            value = text,
            onValueChange = { text = it },
            maxLines = 1,
            placeholder = { Text(text = placeholder) },
            modifier = modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.onPrimary
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = modifier.clickable {
                        onBack()
                    })
            },
            trailingIcon = {
                Icon(imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    modifier = modifier.clickable {
                        onClearResults()
                        text = ""
                    })
            },
        )

        LazyColumn(
            modifier = modifier
                .animateContentSize()
                .padding(all = 8.dp)
        ) {
            items(items = itemList, key = { it.name }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = {
                            text = it.name
                            onItemSelect(it)
                        })
                ) {
                    Icon(Icons.Outlined.LocationOn, null)
                    Text(
                        text = it.name,
                        modifier = Modifier.padding(16.dp),
                    )
                }
                Divider(
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxHeight()
                        .fillMaxWidth()
                )
            }
        }
    }
}

