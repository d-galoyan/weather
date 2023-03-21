package com.example.weather.presentation.screens.addCity

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weather.R
import com.example.weather.presentation.commons.AutoCompleteTextField
import com.example.weather.presentation.commons.Spinner

@Composable
fun AddCityScreen(
    onBack: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val addCityViewModel = hiltViewModel<AddCityViewModel>()
    val predictions = addCityViewModel.predictions
    val saving = addCityViewModel.savingCity

    Box(modifier = Modifier.fillMaxSize()) {
        AutoCompleteTextField(
            placeholder = stringResource(id = R.string.search_places),
            itemList = predictions,
            onClearResults = {
                addCityViewModel.clear()
            },
            onQuery = { addCityViewModel.onSearchChange(it) },
            onBack = onBack,
            onItemSelect = {
                focusManager.clearFocus()
                addCityViewModel.onCitySelect(it) {
                    onBack()
                }
            }
        )
        Spinner(
            spin = saving,
            modifier = Modifier.align(alignment = Alignment.Center)
        )
    }
}