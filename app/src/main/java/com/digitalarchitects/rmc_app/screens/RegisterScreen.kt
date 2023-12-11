package com.digitalarchitects.rmc_app.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.components.ButtonComponent
import com.digitalarchitects.rmc_app.components.CheckboxComponent
import com.digitalarchitects.rmc_app.components.ClickableLoginTextComponent
import com.digitalarchitects.rmc_app.components.DividerTextComponent
import com.digitalarchitects.rmc_app.components.MyTextFieldComponent
import com.digitalarchitects.rmc_app.components.PasswordTextFieldComponent
import com.digitalarchitects.rmc_app.components.RmcAppBar
import com.digitalarchitects.rmc_app.data.register.RegisterUIEvent
import com.digitalarchitects.rmc_app.data.register.RegisterViewModel

@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel = viewModel(),
    onNavigateUp: () -> Unit,
    onTermsAndConditionsTextClicked: (String) -> Unit,
    onLoginTextClicked: (String) -> Unit,
    onRegisterButtonClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            RmcAppBar(
                title = R.string.screen_title_register,
                navigationIcon = Icons.Rounded.ArrowBack,
                navigateUp = { onNavigateUp() }
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
                    MyTextFieldComponent(
                        labelValue = stringResource(id = R.string.first_name),
                        icon = Icons.Default.Person,
                        onTextSelected = {
                            registerViewModel.onEvent(RegisterUIEvent.FirstNameChanged(it))
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier.weight(1f) // Apply weight to distribute horizontal space
                    )
                    MyTextFieldComponent(
                        labelValue = stringResource(id = R.string.last_name),
                        icon = Icons.Default.Person,
                        onTextSelected = {
                            registerViewModel.onEvent(RegisterUIEvent.LastNameChanged(it))

                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier.weight(1f) // Apply weight to distribute horizontal space
                    )
                }

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    icon = Icons.Default.Email,
                    onTextSelected = {
                        registerViewModel.onEvent(RegisterUIEvent.EmailChanged(it))
                    },
                    Modifier.fillMaxWidth()
                )
                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.telephone),
                    icon = Icons.Default.Call,
                    onTextSelected = {
                        registerViewModel.onEvent(RegisterUIEvent.TelephoneChanged(it))
                    },
                    Modifier.fillMaxWidth()
                )
                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    icon = Icons.Default.Lock,
                    onTextSelected = {
                        registerViewModel.onEvent(RegisterUIEvent.PasswordChanged(it))
                    },
                    Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    )
                )
                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.address),
                    icon = Icons.Filled.Home,
                    onTextSelected = {
                        registerViewModel.onEvent(RegisterUIEvent.AddressChanged(it))
                    },
                    Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
                ) {
                    MyTextFieldComponent(
                        labelValue = stringResource(id = R.string.postal_code),
                        icon = Icons.Default.Numbers,
                        onTextSelected = {
                            registerViewModel.onEvent(RegisterUIEvent.PostalCodeChanged(it))
                        },
                        modifier = Modifier.weight(1f) // Apply weight to distribute horizontal space
                    )
                    MyTextFieldComponent(
                        labelValue = stringResource(id = R.string.building_number),
                        icon = Icons.Default.Numbers,
                        onTextSelected = {
                            registerViewModel.onEvent(RegisterUIEvent.BuildingNumberChanged(it))
                        },
                        modifier = Modifier.weight(1f) // Apply weight to distribute horizontal space
                    )
                }

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.city),
                    icon = Icons.Default.LocationCity,
                    onTextSelected = {
                        registerViewModel.onEvent(RegisterUIEvent.CityChanged(it))
                    },
                    Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    )
                )

                CheckboxComponent(
                    value = stringResource(id = R.string.terms_and_conditions),
                    onTextSelected = onTermsAndConditionsTextClicked
                )

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.register),
                    onButtonClicked = {
                        registerViewModel.onEvent(RegisterUIEvent.RegisterButtonClicked)
                        onRegisterButtonClicked()
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(
                    tryingToLogin = true,
                    onTextSelected = onLoginTextClicked
                )
            }
        }
    }
}