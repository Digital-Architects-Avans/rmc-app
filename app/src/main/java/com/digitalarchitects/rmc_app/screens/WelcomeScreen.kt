package com.digitalarchitects.rmc_app.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.components.RmcFilledButton
import com.digitalarchitects.rmc_app.components.RmcFilledTonalButton
import com.digitalarchitects.rmc_app.components.RmcLogoText
import com.digitalarchitects.rmc_app.components.RmcSpacer
import com.digitalarchitects.rmc_app.data.welcome.WelcomeUIEvent
import com.digitalarchitects.rmc_app.data.welcome.WelcomeViewModel

@Composable
fun WelcomeScreen(
    welcomeViewModel: WelcomeViewModel = viewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            RmcLogoText()

            RmcSpacer()

            Text(
                text = "Welcome to Rent My Car",
                style = MaterialTheme.typography.titleLarge
            )

            RmcSpacer(8)

            Text(
                text = "Join a movement towards a green planet with shared mobility of everyone, for everyone.",
                style = MaterialTheme.typography.bodyLarge
            )

            RmcSpacer()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(Modifier.weight(1f)) {
                    RmcFilledTonalButton(value = stringResource(id = R.string.register),
                        onClick = { welcomeViewModel.onEvent(WelcomeUIEvent.RegisterButtonClicked) })
                }
                Column(Modifier.weight(1f)) {
                    RmcFilledButton(value = stringResource(id = R.string.login),
                        onClick = { welcomeViewModel.onEvent(WelcomeUIEvent.LoginButtonClicked) })
                }
            }

        }
    }
}