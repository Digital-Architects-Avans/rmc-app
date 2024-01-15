package com.digitalarchitects.rmc_app.presentation.screens.editmyvehicle

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.domain.model.EngineType
import com.digitalarchitects.rmc_app.presentation.RmcScreen
import com.digitalarchitects.rmc_app.presentation.components.AddressEdit
import com.digitalarchitects.rmc_app.presentation.components.RmcAppBar
import com.digitalarchitects.rmc_app.presentation.components.RmcDivider
import com.digitalarchitects.rmc_app.presentation.components.RmcFilledButton
import com.digitalarchitects.rmc_app.presentation.components.RmcOutlinedButton
import com.digitalarchitects.rmc_app.presentation.components.RmcSpacer
import com.digitalarchitects.rmc_app.presentation.components.RmcSwitch
import com.digitalarchitects.rmc_app.presentation.components.RmcTextField
import com.digitalarchitects.rmc_app.presentation.components.getImageByLicensePlate
import com.digitalarchitects.rmc_app.presentation.screens.registervehicle.RegisterVehicleUIEvent
import com.digitalarchitects.rmc_app.presentation.screens.search.RmcFilterChip

@Composable
fun EditMyVehicleScreen(
    viewModel: EditMyVehicleViewModel,
    navigateToScreen: (String) -> Unit,
    vehicleId: String?
) {
    val uiState by viewModel.uiState.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val placesPredictions by viewModel.placePredictions.collectAsState()

    LaunchedEffect(vehicleId) {
        if (vehicleId != null) {
            viewModel.fetchVehicleDetails(vehicleId)
        }
    }

    Scaffold(
        topBar = {
            RmcAppBar(
                title = R.string.screen_title_edit_vehicle,
                navigationIcon = Icons.Rounded.ArrowBack,
                navigateUp = {
                    navigateToScreen(RmcScreen.MyVehicles.name)
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
                val vehicleImage = getImageByLicensePlate(uiState.licensePlate)
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(height = 160.dp, width = 20.dp),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(vehicleImage),
                    contentDescription = null
                )

                RmcSpacer(24)

                Column(
                    modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_large))
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
                    ) {

                        RmcTextField(
                            label = stringResource(id = R.string.brand),
                            value = uiState.brand,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            ),
                            onValueChange = {
                                viewModel.onEvent(EditMyVehicleUIEvent.SetBrand(it))
                            },
                            modifier = Modifier.weight(0.6f)
                        )

                        RmcTextField(
                            label = stringResource(id = R.string.year),
                            value = uiState.year.toString(),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            ),
                            onValueChange = {
                                viewModel.onEvent(EditMyVehicleUIEvent.SetYear(it.toInt()))
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
                            value = uiState.model,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            ),
                            onValueChange = {
                                viewModel.onEvent(EditMyVehicleUIEvent.SetModel(it))
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
                            value = uiState.licensePlate,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            ),
                            onValueChange = {
                                viewModel.onEvent(EditMyVehicleUIEvent.SetLicensePlate(it))
                            },
                            modifier = Modifier.weight(1f)
                        )

                        RmcTextField(
                            label = stringResource(id = R.string.price),
                            value = uiState.price.toString(),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Decimal,
                                imeAction = ImeAction.Next
                            ),
                            onValueChange = {
                                viewModel.onEvent(EditMyVehicleUIEvent.SetPrice(it.toDouble()))
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
                            value = uiState.vehicleClass,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            ),
                            onValueChange = {
                                viewModel.onEvent(EditMyVehicleUIEvent.SetVehicleClass(it))
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    RmcSpacer(16)

                    Text(
                        text = stringResource(id = R.string.engine_type),
                        style = MaterialTheme.typography.titleMedium,
                    )
                    RmcSpacer(8)
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
                                    viewModel.onEvent(EditMyVehicleUIEvent.EngineTypeICEButtonClicked)
                                }
                            )
                        }
                        Column(Modifier.weight(1f)) {
                            RmcFilterChip(
                                label = stringResource(id = R.string.engine_type_bev),
                                selected = uiState.engineType == EngineType.BEV,
                                onClick = {
                                    viewModel.onEvent(EditMyVehicleUIEvent.EngineTypeBEVButtonClicked)
                                }
                            )
                        }
                        Column(Modifier.weight(1f)) {
                            RmcFilterChip(
                                label = stringResource(id = R.string.engine_type_fcev),
                                selected = uiState.engineType == EngineType.FCEV,
                                onClick = {
                                    viewModel.onEvent(EditMyVehicleUIEvent.EngineTypeFCEVButtonClicked)
                                }
                            )
                        }
                    }

                    RmcDivider()

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = stringResource(id = R.string.vehicle_availability))
                        Switch(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.End),
                            checked = uiState.availability,
                            onCheckedChange = { viewModel.onEvent(EditMyVehicleUIEvent.AvailabilityToggleButtonClicked) }
                        )
                    }

                    RmcSpacer(8)

                    // Add the larger RmcTextField for the vehicle description
                    RmcTextField(
                        label = stringResource(id = R.string.vehicle_description),
                        value = uiState.description,
                        maxLines = 5,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        onValueChange = {
                            viewModel.onEvent(EditMyVehicleUIEvent.SetDescription(it))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp) // Adjust the height as needed
                    )

                    RmcSpacer(16)

                    AddressEdit(
                        query = uiState.query,
                        modifier = Modifier,
                        addressPlaceItemPredictions = placesPredictions,
                        onQueryChanged = { query ->
                            viewModel.onEvent(EditMyVehicleUIEvent.OnAddressChange(query))
                        },
                        onClearClick = {
                            viewModel.onEvent(EditMyVehicleUIEvent.OnAddressAutoCompleteClear)
                        },
                        onDoneClick = if (placesPredictions.isNotEmpty()) {
                            {
                                viewModel.onEvent(
                                    EditMyVehicleUIEvent.OnAddressSelected(
                                        placesPredictions.first()
                                    )
                                )
                            }
                        } else {
                            { keyboardController?.hide() }
                        },
                        onItemClick = { placeItem ->
                            viewModel.onEvent(EditMyVehicleUIEvent.OnAddressSelected(placeItem))
                        }
                    )

                    RmcSpacer(16)

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
                    ) {
                        Column(Modifier.weight(1f)) {
                            RmcOutlinedButton(
                                value = stringResource(id = R.string.cancel),
                                onClick = {
                                    navigateToScreen(RmcScreen.MyVehicles.name)
                                }
                            )
                        }
                        Column(Modifier.weight(1f)) {
                            RmcFilledButton(
                                value = stringResource(id = R.string.apply),
                                onClick = {
                                    viewModel.onEvent(EditMyVehicleUIEvent.ConfirmEditMyVehicleButtonClicked)
                                }
                            )
                        }
                    }
                    RmcSpacer(32)
                }

                val context = LocalContext.current
                val toastMessage = if (uiState.vehicleUpdated) {
                    stringResource(R.string.vehicle_updated_successfully)
                } else {
                    stringResource(R.string.unable_to_update_vehicle)
                }
                if (uiState.vehicleUpdated) {
                    Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
                    navigateToScreen(RmcScreen.MyVehicles.name)
                    viewModel.onEvent(EditMyVehicleUIEvent.ResetVehicleUpdated)
                }
            }
        }
    }
}