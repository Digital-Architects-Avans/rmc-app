package com.digitalarchitects.rmc_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.components.RmcAppBar
import com.digitalarchitects.rmc_app.components.RmcFilledButton
import com.digitalarchitects.rmc_app.components.RmcSpacer
import com.digitalarchitects.rmc_app.components.RmcSwitch
import com.digitalarchitects.rmc_app.components.RmcTextField
import com.digitalarchitects.rmc_app.data.registervehicle.RegisterVehicleUIEvent
import com.digitalarchitects.rmc_app.data.registervehicle.RegisterVehicleViewModel
import com.digitalarchitects.rmc_app.model.EngineType

@Composable
fun RegisterVehicleScreen(
    viewModel: RegisterVehicleViewModel,
    navigateToScreen: (String) -> Unit
) {
    val navigateToScreenEvent by viewModel.navigateToScreen.collectAsState()
    if (navigateToScreenEvent != null) {
        navigateToScreen(navigateToScreenEvent!!.name)
    }
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
//
    }
    Scaffold(
        topBar = {
            RmcAppBar(
                title = R.string.screen_title_register_vehicle,
                navigationIcon = Icons.Rounded.ArrowBack,
                navigateUp = {
                    viewModel.onEvent(RegisterVehicleUIEvent.NavigateUpButtonClicked)
                },
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
                    .verticalScroll(rememberScrollState())
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(height = 160.dp, width = 20.dp),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(R.drawable.civic),
                    contentDescription = null
                )

                RmcSpacer(8)

                Column(
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(R.dimen.padding_large),
                            end = dimensionResource(R.dimen.padding_large)
                        )
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
                    ) {

                        RmcTextField(
                            label = stringResource(id = R.string.brand),
//                            icon = Icons.Filled.Person,
                            value = uiState.brand,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            ),
                            onValueChange = {
                                viewModel.onEvent(RegisterVehicleUIEvent.SetBrand(it))
                            },
                            modifier = Modifier.weight(0.6f)
                        )

                        RmcTextField(
                            label = stringResource(id = R.string.year),
//                            icon = Icons.Filled.Person,
                            value = uiState.year,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            ),
                            onValueChange = {
                                viewModel.onEvent(RegisterVehicleUIEvent.SetYear(it.toInt()))
                            },
                            modifier = Modifier.weight(0.4f)
                        )

                    }

                    RmcSpacer(8)

                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
                    ) {

                        RmcTextField(
                            label = stringResource(id = R.string.model),
//                            icon = Icons.Filled.Person,
                            value = uiState.model,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            ),
                            onValueChange = {
                                viewModel.onEvent(RegisterVehicleUIEvent.SetModel(it))
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    RmcSpacer(8)

                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
                    ) {

                        RmcTextField(
                            label = stringResource(id = R.string.license_plate),
//                            icon = Icons.Filled.Person,
                            value = uiState.licensePlate,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            ),
                            onValueChange = {
                                viewModel.onEvent(RegisterVehicleUIEvent.SetLicensePlate(it))
                            },
                            modifier = Modifier.weight(1f)
                        )

                        RmcTextField(
                            label = stringResource(id = R.string.price),
//                            icon = Icons.Filled.Person,
                            value = uiState.price,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Decimal,
                                imeAction = ImeAction.Next
                            ),
                            onValueChange = {
                                viewModel.onEvent(RegisterVehicleUIEvent.SetPrice(it.toDouble()))
                            },
                            modifier = Modifier.weight(1f)
                        )

                    }

                    RmcSpacer(8)

                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
                    ) {
                        RmcTextField(
                            label = stringResource(id = R.string.vehicle_class),
//                            icon = Icons.Filled.Person,
                            value = uiState.vehicleClass,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            ),
                            onValueChange = {
                                viewModel.onEvent(RegisterVehicleUIEvent.SetVehicleClass(it))
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    RmcSpacer(8)

                    Text(text = stringResource(id = R.string.engine_type))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large))
                    ) {
                        Column(Modifier.weight(1f)) {
                            RmcFilterChip(
                                label = stringResource(id = R.string.engine_type_ice),
                                selected = uiState.engineType == EngineType.ICE,
                                onClick = {
                                    viewModel.onEvent(RegisterVehicleUIEvent.EngineTypeICEButtonClicked)
                                }
                            )
                        }
                        Column(Modifier.weight(1f)) {
                            RmcFilterChip(
                                label = stringResource(id = R.string.engine_type_bev),
                                selected = uiState.engineType == EngineType.BEV,
                                onClick = {
                                    viewModel.onEvent(RegisterVehicleUIEvent.EngineTypeBEVButtonClicked)
                                }
                            )
                        }
                        Column(Modifier.weight(1f)) {
                            RmcFilterChip(
                                label = stringResource(id = R.string.engine_type_fcev),
                                selected = uiState.engineType == EngineType.FCEV,
                                onClick = {
                                    viewModel.onEvent(RegisterVehicleUIEvent.EngineTypeFCEVButtonClicked)
                                }
                            )
                        }
                    }
                    RmcSpacer(8)

                    Divider()

                    RmcSpacer(8)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
//                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = stringResource(id = R.string.vehicle_availability),
                            fontWeight = FontWeight.Bold
                        )
                        RmcSwitch(value = uiState.availability, onToggle = {
                            viewModel.onEvent(RegisterVehicleUIEvent.AvailabilityToggleButtonClicked)
                        })
                    }

                    RmcSpacer(8)


                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large))
                    ) {
                        RmcTextField(
                            label = stringResource(id = R.string.location),
//                            icon = Icons.Filled.Person,
                            value = uiState.latitude,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            onValueChange = {
                                viewModel.onEvent(RegisterVehicleUIEvent.SetLatitude(it.toFloat()))
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    RmcSpacer(32)

                    RmcFilledButton(
                        value = stringResource(id = R.string.register),
                        onClick = {
                            viewModel.onEvent(RegisterVehicleUIEvent.ConfirmRegisterVehicleButtonClicked)
                        }
                    )
                }
            }
        }
    }
}

