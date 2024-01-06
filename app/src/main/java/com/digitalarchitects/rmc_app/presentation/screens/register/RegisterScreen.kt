package com.digitalarchitects.rmc_app.presentation.screens.register

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.data.auth.AuthResult
import com.digitalarchitects.rmc_app.presentation.RmcScreen
import com.digitalarchitects.rmc_app.presentation.components.CheckboxComponent
import com.digitalarchitects.rmc_app.presentation.components.ClickableLoginTextComponent
import com.digitalarchitects.rmc_app.presentation.components.DividerTextComponent
import com.digitalarchitects.rmc_app.presentation.components.RmcAppBar
import com.digitalarchitects.rmc_app.presentation.components.RmcFilledButton
import com.digitalarchitects.rmc_app.presentation.components.RmcSpacer
import com.digitalarchitects.rmc_app.presentation.components.RmcTextField

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
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
                        "You're not authorized",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is AuthResult.NoConnectionError -> {
                    Toast.makeText(
                        context,
                        "No connection. Please try again later.",
                        Toast.LENGTH_SHORT
                    ).show()
                    navigateToScreen(RmcScreen.Welcome.name)
                }

                is AuthResult.UnknownError -> {
                    Toast.makeText(
                        context,
                        "Unknown error occurred. Please try again later.",
                        Toast.LENGTH_SHORT
                    ).show()
                    navigateToScreen(RmcScreen.Welcome.name)
                }
            }
        }
    }


    Scaffold(
        topBar = {
            RmcAppBar(
                title = R.string.screen_title_register,
                navigationIcon = Icons.Rounded.ArrowBack,
                navigateUp = {
                    navigateToScreen(RmcScreen.Welcome.name)
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = dimensionResource(R.dimen.padding_large),
                        end = dimensionResource(R.dimen.padding_large)
                    )
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
                ) {
                    RmcTextField(
                        label = stringResource(id = R.string.first_name),
                        icon = Icons.Filled.Person,
                        value = uiState.firstName,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        onValueChange = {
                            viewModel.onEvent(RegisterUIEvent.FirstNameChanged(it))
                        },
                        modifier = Modifier.weight(1f)
                    )
                    RmcTextField(
                        label = stringResource(id = R.string.last_name),
                        icon = Icons.Filled.Person,
                        value = uiState.lastName,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        onValueChange = {
                            viewModel.onEvent(RegisterUIEvent.LastNameChanged(it))
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                RmcSpacer(8)

                RmcTextField(
                    label = stringResource(id = R.string.email),
                    icon = Icons.Filled.Email,
                    value = uiState.email,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        viewModel.onEvent(RegisterUIEvent.EmailChanged(it))
                    }
                )

                RmcSpacer(8)

                RmcTextField(
                    label = stringResource(id = R.string.telephone),
                    icon = Icons.Filled.Call,
                    value = uiState.telephone,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        viewModel.onEvent(RegisterUIEvent.TelephoneChanged(it))
                    }
                )

                RmcSpacer(8)

                RmcTextField(
                    label = stringResource(id = R.string.address),
                    icon = Icons.Filled.Home,
                    value = uiState.address,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        viewModel.onEvent(RegisterUIEvent.AddressChanged(it))
                    }
                )

                RmcSpacer(8)

                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
                ) {
                    RmcTextField(
                        label = stringResource(id = R.string.postal_code),
                        icon = Icons.Filled.Numbers,
                        value = uiState.postalCode,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        onValueChange = {
                            viewModel.onEvent(RegisterUIEvent.PostalCodeChanged(it))
                        },
                        modifier = Modifier.weight(1f)
                    )
                    RmcTextField(
                        label = stringResource(id = R.string.building_number),
                        icon = Icons.Filled.Numbers,
                        value = uiState.buildingNumber,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        onValueChange = {
                            viewModel.onEvent(RegisterUIEvent.BuildingNumberChanged(it))
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                RmcSpacer(8)

                RmcTextField(
                    label = stringResource(id = R.string.city),
                    icon = Icons.Filled.LocationCity,
                    value = uiState.city,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    onValueChange = {
                        viewModel.onEvent(RegisterUIEvent.CityChanged(it))
                    }
                )

                RmcSpacer(8)

                RmcTextField(
                    label = stringResource(id = R.string.password),
                    icon = Icons.Filled.Lock,
                    value = uiState.password,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    isPassword = true,
                    onValueChange = {
                        viewModel.onEvent(RegisterUIEvent.PasswordChanged(it))
                    }
                )

                CheckboxComponent(
                    value = stringResource(id = R.string.terms_and_conditions),
                    onTextSelected = { /* TODO */ }
                )

                RmcSpacer(32)

                RmcFilledButton(
                    value = stringResource(id = R.string.register),
                    onClick = {
                        viewModel.onEvent(RegisterUIEvent.RegisterButtonClicked)
                    }
                )

                DividerTextComponent()

                ClickableLoginTextComponent(
                    tryingToLogin = true,
                    onTextSelected = { navigateToScreen(RmcScreen.Login.name) }

                )

                if (uiState.isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        viewModel = viewModel(),
        navigateToScreen = { }
    )
}

