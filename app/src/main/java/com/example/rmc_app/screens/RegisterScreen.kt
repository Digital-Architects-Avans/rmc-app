package com.example.rmc_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.rmc_app.R
import com.example.rmc_app.app.RmcScreen
import com.example.rmc_app.components.ButtonComponent
import com.example.rmc_app.components.CheckboxComponent
import com.example.rmc_app.components.ClickableLoginTextComponent
import com.example.rmc_app.components.DividerTextComponent
import com.example.rmc_app.components.MyTextFieldComponent
import com.example.rmc_app.components.PasswordTextFieldComponent
import com.example.rmc_app.data.register.RegisterUIEvent
import com.example.rmc_app.data.register.RegisterViewModel

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    registerViewModel: RegisterViewModel = viewModel(),
    navController: NavController
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(Modifier.fillMaxWidth()) {
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
                    Spacer(modifier = Modifier.width(10.dp))
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

                Row(Modifier.fillMaxWidth()) {
                    MyTextFieldComponent(
                        labelValue = stringResource(id = R.string.postal_code),
                        icon = Icons.Default.Numbers,
                        onTextSelected = {
                            registerViewModel.onEvent(RegisterUIEvent.PostalCodeChanged(it))
                        },
                        modifier = Modifier.weight(1f) // Apply weight to distribute horizontal space
                    )
                    Spacer(modifier = Modifier.width(10.dp))
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

                CheckboxComponent(value = stringResource(id = R.string.terms_and_conditions),
                    onTextSelected = {
                        navController.navigate(RmcScreen.TermsAndConditions.name)
                    }
                )

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.register),
                    onButtonClicked = {
                        registerViewModel.onEvent(RegisterUIEvent.RegisterButtonClicked)
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(
                    tryingToLogin = true,
                    onTextSelected = {
                        navController.navigate(RmcScreen.Login.name)
                    })

            }
        }
    }
}