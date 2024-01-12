package com.digitalarchitects.rmc_app.presentation.screens.editmyaccount

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.presentation.RmcScreen
import com.digitalarchitects.rmc_app.presentation.components.RmcAppBar
import com.digitalarchitects.rmc_app.presentation.components.RmcFilledButton
import com.digitalarchitects.rmc_app.presentation.components.RmcOutlinedButton
import com.digitalarchitects.rmc_app.presentation.components.RmcSpacer
import com.digitalarchitects.rmc_app.presentation.components.RmcTextField


@Composable
fun EditMyAccountScreen(
    viewModel: EditMyAccountViewModel,
    navigateToScreen: (String) -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()
    val userUpdated by viewModel.userUpdated.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(EditMyAccountUIEvent.FetchUser)
    }

    Scaffold(
        topBar = {
            RmcAppBar(
                title = R.string.screen_title_edit_account,
                navigationIcon = Icons.Rounded.ArrowBack,
                navigateUp = {
                    navigateToScreen(RmcScreen.MyAccount.name)
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
                            viewModel.onEvent(EditMyAccountUIEvent.SetFirstName(it))
                        },
                        modifier = Modifier.weight(1f)
                    )
                    RmcTextField(
                        label = stringResource(id = R.string.last_name),
                        leadingIcon = Icons.Filled.Person,
                        value = uiState.lastName,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        onValueChange = {
                            viewModel.onEvent(EditMyAccountUIEvent.SetLastName(it))
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
                        viewModel.onEvent(EditMyAccountUIEvent.SetEmail(it))
                    }
                )

                RmcSpacer(8)

                RmcTextField(
                    label = stringResource(id = R.string.telephone),
                    leadingIcon = Icons.Filled.Call,
                    value = uiState.phone,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        viewModel.onEvent(EditMyAccountUIEvent.SetPhone(it))
                    }
                )

                RmcSpacer(8)

                RmcTextField(
                    label = stringResource(id = R.string.address),
                    leadingIcon = Icons.Filled.Home,
                    value = uiState.street,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        viewModel.onEvent(EditMyAccountUIEvent.SetStreet(it))
                    }
                )

                RmcSpacer(8)

                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
                ) {
                    RmcTextField(
                        label = stringResource(id = R.string.postal_code),
                        leadingIcon = Icons.Filled.Numbers,
                        value = uiState.zipCode,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        onValueChange = {
                            viewModel.onEvent(EditMyAccountUIEvent.SetZipCode(it))
                        },
                        modifier = Modifier.weight(1f)
                    )
                    RmcTextField(
                        label = stringResource(id = R.string.building_number),
                        leadingIcon = Icons.Filled.Numbers,
                        value = uiState.buildingNumber,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        onValueChange = {
                            viewModel.onEvent(EditMyAccountUIEvent.SetBuildingNumber(it))
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
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        viewModel.onEvent(EditMyAccountUIEvent.SetCity(it))
                    }
                )

                RmcSpacer(8)

                RmcTextField(
                    label = stringResource(id = R.string.password),
                    leadingIcon = Icons.Filled.Lock,
                    value = uiState.password,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    isPassword = true,
                    onValueChange = {
                        viewModel.onEvent(EditMyAccountUIEvent.SetPassword(it))
                    }
                )

                RmcSpacer(32)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
                ) {
                    Column(Modifier.weight(1f)) {
                        RmcFilledButton(
                            value = stringResource(id = R.string.apply),
                            onClick = {
                                viewModel.onEvent(EditMyAccountUIEvent.ConfirmEditMyAccountButtonClicked)
                            }
                        )
                    }
                    Column(Modifier.weight(1f)) {
                        RmcOutlinedButton(
                            value = stringResource(id = R.string.cancel),
                            onClick = {
                                navigateToScreen(RmcScreen.MyAccount.name)
                            }
                        )
                    }
                }
                val context = LocalContext.current
                val toastMessage = if (userUpdated) {
                    stringResource(R.string.changes_saved)
                } else {
                    stringResource(R.string.unable_to_save_changes_try_again)
                }
                if (userUpdated) {
                    Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
                    navigateToScreen(RmcScreen.MyAccount.name)
                    viewModel.onEvent(EditMyAccountUIEvent.ResetUserUpdated)
                }
            }
        }
    }
}
