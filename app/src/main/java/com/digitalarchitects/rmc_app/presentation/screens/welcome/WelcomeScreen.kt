package com.digitalarchitects.rmc_app.presentation.screens.welcome

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.data.auth.AuthResult
import com.digitalarchitects.rmc_app.presentation.RmcScreen
import com.digitalarchitects.rmc_app.presentation.components.RmcFilledButton
import com.digitalarchitects.rmc_app.presentation.components.RmcFilledTonalButton
import com.digitalarchitects.rmc_app.presentation.components.RmcLogoText
import com.digitalarchitects.rmc_app.presentation.components.RmcSpacer

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel,
    navigateToScreen: (String) -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(viewModel, context) {
        viewModel.authResult.collect { result ->
            when (result) {
                is AuthResult.Authorized -> {
                    navigateToScreen(RmcScreen.RentACar.name)
                }

                is AuthResult.Unauthorized -> {
                    Toast.makeText(
                        context,
                        "Log in or create an account.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is AuthResult.NoConnectionError -> {
                    Toast.makeText(
                        context,
                        "No connection. Please try again later.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is AuthResult.UnknownError -> {
                    Toast.makeText(
                        context,
                        "Unknown error occurred. Please try again later.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.padding_large))
                .verticalScroll(rememberScrollState())
        ) {
            RmcLogoText()

            RmcSpacer()

            Text(
                text = stringResource(R.string.welcome_title),
                style = MaterialTheme.typography.titleLarge
            )

            RmcSpacer(8)

            Text(
                text = stringResource(R.string.welcome_body),
                style = MaterialTheme.typography.bodyLarge
            )

            RmcSpacer()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
            ) {
                Column(Modifier.weight(1f)) {
                    RmcFilledTonalButton(
                        value = stringResource(id = R.string.register),
                        onClick = {
                            navigateToScreen(RmcScreen.Register.name)
                        }
                    )
                }
                Column(Modifier.weight(1f)) {
                    RmcFilledButton(
                        value = stringResource(id = R.string.login),
                        onClick = {
                            navigateToScreen(RmcScreen.Login.name)
                        }
                    )
                }
            }

        }
    }
}