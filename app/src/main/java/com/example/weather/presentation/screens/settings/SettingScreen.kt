package com.example.weather.presentation.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weather.R
import com.example.weather.domain.models.TempUnit
import com.example.weather.domain.models.ThemeMode

@Composable
fun SettingScreen() {

    val settingsViewModel: SettingsViewModel = hiltViewModel()
    val settings by settingsViewModel.settings.collectAsState()

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.dark_mode),
                style = MaterialTheme.typography.h6
            )
            Switch(
                checked = settingsViewModel.isDark,
                onCheckedChange = {
                    settingsViewModel.setThemeMode(
                        if (settingsViewModel.isDark) ThemeMode.Light else ThemeMode.Dark
                    )
                },
                colors = SwitchDefaults.colors(
                    uncheckedThumbColor = MaterialTheme.colors.secondary,
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.temperature_unit),
                style = MaterialTheme.typography.h6,
            )
            Column {
                for (unit in TempUnit.values()) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        RadioButton(
                            selected = unit == settings.tempUnit,
                            onClick = { settingsViewModel.setTempUnit(unit) }
                        )
                        Text(text = unit.name)
                    }
                }
            }
        }
    }
}