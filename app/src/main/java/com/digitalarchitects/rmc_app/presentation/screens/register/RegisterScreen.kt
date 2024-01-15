package com.digitalarchitects.rmc_app.presentation.screens.register

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Person
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
                    viewModel.getDataFromRemoteSource()
                    navigateToScreen(RmcScreen.RentACar.name)
                }

                is AuthResult.Unauthorized -> {
                    Toast.makeText(
                        context,
                        R.string.you_re_not_authorized,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is AuthResult.NoConnectionError -> {
                    Toast.makeText(
                        context,
                        R.string.no_connection_please_try_again_later,
                        Toast.LENGTH_SHORT
                    ).show()
                    navigateToScreen(RmcScreen.Welcome.name)
                }

                is AuthResult.UnknownError -> {
                    Toast.makeText(
                        context,
                        R.string.unknown_error_occurred_please_try_again_later,
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
                navigationIcon = Icons.AutoMirrored.Rounded.ArrowBack,
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
                        leadingIcon = Icons.Filled.Person,
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
                    leadingIcon = Icons.Filled.Email,
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
                    leadingIcon = Icons.Filled.Call,
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
                    leadingIcon = Icons.Filled.Home,
                    value = uiState.address,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
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
                        label = stringResource(id = R.string.building_number),
                        leadingIcon = Icons.Filled.Numbers,
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
                    RmcTextField(
                        label = stringResource(id = R.string.postal_code),
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
                }

                RmcSpacer(8)

                RmcTextField(
                    label = stringResource(id = R.string.city),
                    leadingIcon = Icons.Filled.LocationCity,
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
                    leadingIcon = Icons.Filled.Lock,
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
                    onTextSelected = { navigateToScreen(RmcScreen.TermsAndConditions.name) },
                )

                RmcSpacer(8)

                RmcFilledButton(
                    value = stringResource(id = R.string.register),
                    isEnabled = uiState.firstName.isNotEmpty() &&
                            uiState.lastName.isNotEmpty() &&
                            uiState.email.isNotEmpty() &&
                            uiState.telephone.isNotEmpty() &&
                            uiState.address.isNotEmpty() &&
                            uiState.buildingNumber.isNotEmpty() &&
                            uiState.postalCode.isNotEmpty() &&
                            uiState.city.isNotEmpty() &&
                            uiState.password.isNotEmpty(),
                    onClick = {
                        viewModel.onEvent(RegisterUIEvent.RegisterButtonClicked)
                    }
                )

                DividerTextComponent()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ClickableLoginTextComponent(
                        tryingToLogin = true,
                        onTextSelected = { navigateToScreen(RmcScreen.Login.name) }

                    )
                }

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

                RmcSpacer()
            }
        }
    }
}