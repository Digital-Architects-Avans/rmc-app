package com.digitalarchitects.rmc_app.screens

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.components.CheckboxComponent
import com.digitalarchitects.rmc_app.components.ClickableLoginTextComponent
import com.digitalarchitects.rmc_app.components.DividerTextComponent
import com.digitalarchitects.rmc_app.components.RmcAppBar
import com.digitalarchitects.rmc_app.components.RmcFilledButton
import com.digitalarchitects.rmc_app.components.RmcSpacer
import com.digitalarchitects.rmc_app.components.RmcTextField
import com.digitalarchitects.rmc_app.data.editmyaccount.EditMyAccountUIEvent
import com.digitalarchitects.rmc_app.data.register.RegisterUIEvent
import com.digitalarchitects.rmc_app.data.register.RegisterViewModel
import com.digitalarchitects.rmc_app.data.welcome.WelcomeViewModel

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    navigateToScreen: (String) -> Unit
) {
    val navigateToScreenEvent by viewModel.navigateToScreen.collectAsState()
    if (navigateToScreenEvent != null) {
        navigateToScreen(navigateToScreenEvent!!.name)
    }
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
//  TODO implement default state
    }

    Scaffold(
        topBar = {
            RmcAppBar(
                title = R.string.screen_title_register,
                navigationIcon = Icons.Rounded.ArrowBack,
                navigateUp = {
                    viewModel.onEvent(RegisterUIEvent.NavigateUpButtonClicked)
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
                        value = "registerUIState.firstName",
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        onValueChange = {
//                          TODO  registerViewModel.onEvent(RegisterUIEvent.FirstNameChanged(it))
                        },
                        modifier = Modifier.weight(1f)
                    )
                    RmcTextField(
                        label = stringResource(id = R.string.last_name),
                        icon = Icons.Filled.Person,
                        value = "registerUIState.lastName,",
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        onValueChange = {
//                        TODO    registerViewModel.onEvent(RegisterUIEvent.LastNameChanged(it))
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                RmcSpacer(8)

                RmcTextField(
                    label = stringResource(id = R.string.email),
                    icon = Icons.Filled.Email,
                    value = "registerUIState.email",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        //TODO registerViewModel.onEvent(RegisterUIEvent.EmailChanged(it))
                    }
                )

                RmcSpacer(8)

                RmcTextField(
                    label = stringResource(id = R.string.telephone),
                    icon = Icons.Filled.Call,
                    value = "registerUIState.telephone",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
//                       TODO registerViewModel.onEvent(RegisterUIEvent.TelephoneChanged(it))
                    }
                )

                RmcSpacer(8)

                RmcTextField(
                    label = stringResource(id = R.string.password),
                    icon = Icons.Filled.Lock,
                    value = "registerUIState.password",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    isPassword = true,
                    onValueChange = {
//                       TODO registerViewModel.onEvent(RegisterUIEvent.PasswordChanged(it))
                    }
                )

                RmcSpacer(8)

                RmcTextField(
                    label = stringResource(id = R.string.address),
                    icon = Icons.Filled.Home,
                    value = "registerUIState.address",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
//                    TODO    registerViewModel.onEvent(RegisterUIEvent.AddressChanged(it))
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
                        value = "registerUIState.postalCode",
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        onValueChange = {
//                         TODO   registerViewModel.onEvent(RegisterUIEvent.PostalCodeChanged(it))
                        },
                        modifier = Modifier.weight(1f)
                    )
                    RmcTextField(
                        label = stringResource(id = R.string.building_number),
                        icon = Icons.Filled.Numbers,
                        value = "registerUIState.buildingNumber",
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        onValueChange = {
                        // TODO    registerViewModel.onEvent(RegisterUIEvent.BuildingNumberChanged(it))
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                RmcSpacer(8)

                RmcTextField(
                    label = stringResource(id = R.string.city),
                    icon = Icons.Filled.LocationCity,
                    value = "registerUIState.city",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    onValueChange = {
//                    TODO    registerViewModel.onEvent(RegisterUIEvent.CityChanged(it))
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
                    onTextSelected = {viewModel.onEvent(RegisterUIEvent.LoginButtonClicked)}

                )
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

