package com.digitalarchitects.rmc_app.presentation.screens.registervehicle

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.HorizontalDivider
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
import com.digitalarchitects.rmc_app.domain.model.AddressItem
import com.digitalarchitects.rmc_app.domain.model.EngineType
import com.digitalarchitects.rmc_app.domain.model.PlaceItem
import com.digitalarchitects.rmc_app.presentation.RmcScreen
import com.digitalarchitects.rmc_app.presentation.components.AutoCompleteUI
import com.digitalarchitects.rmc_app.presentation.components.RmcAppBar
import com.digitalarchitects.rmc_app.presentation.components.RmcFilledButton
import com.digitalarchitects.rmc_app.presentation.components.RmcOutlinedButton
import com.digitalarchitects.rmc_app.presentation.components.RmcSpacer
import com.digitalarchitects.rmc_app.presentation.components.RmcSwitch
import com.digitalarchitects.rmc_app.presentation.components.RmcTextField
import com.digitalarchitects.rmc_app.presentation.screens.search.RmcFilterChip

@Composable
fun RegisterVehicleScreen(
    viewModel: RegisterVehicleViewModel,
    navigateToScreen: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    val keyboardController = LocalSoftwareKeyboardController.current

    val address by viewModel.address.collectAsState()
    val placesPredictions by viewModel.placePredictions.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(RegisterVehicleUIEvent.FetchUserId)
    }

    Scaffold(
        topBar = {
            RmcAppBar(
                title = R.string.screen_title_register_vehicle,
                navigationIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                navigateUp = {
                    navigateToScreen(RmcScreen.MyVehicles.name)
                },
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .imePadding(),
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
                            value = uiState.year.toString(),
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
                            value = uiState.price.toString(),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Decimal,
                                imeAction = ImeAction.Next
                            ),
                            onValueChange = { input ->
                                // Check if the input is a valid float before updating the state
                                if (input.isEmpty() || input.toDoubleOrNull() != null) {
                                    viewModel.onEvent(RegisterVehicleUIEvent.SetPrice(input.toDouble()))
                                }
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

                    HorizontalDivider()

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

                    AddressEdit(
                        addressItem = address,
                        modifier = Modifier,
                        addressPlaceItemPredictions = placesPredictions,
                        onQueryChanged = { query ->
                            viewModel.onEvent(RegisterVehicleUIEvent.OnAddressChange(query))
                        },
                        onClearClick = {
                            viewModel.onEvent(RegisterVehicleUIEvent.OnAddressAutoCompleteClear)
                        },
                        onDoneClick = if (placesPredictions.isNotEmpty()) {
                            {
                                viewModel.onEvent(
                                    RegisterVehicleUIEvent.OnAddressSelected(
                                        placesPredictions.first()
                                    )
                                )
                            }
                        } else {
                            { keyboardController?.hide() }
                        },
                        onItemClick = { placeItem ->
                            viewModel.onEvent(RegisterVehicleUIEvent.OnAddressSelected(placeItem))
                        }
                    )
                }

                RmcSpacer(32)

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
                ) {
                    Column(Modifier.weight(1f)) {
                        RmcFilledButton(
                            value = stringResource(id = R.string.register),
                            onClick = {
                                viewModel.onEvent(RegisterVehicleUIEvent.ConfirmRegisterVehicleButtonClicked)
                            }
                        )
                    }
                    Column(Modifier.weight(1f)) {
                        RmcOutlinedButton(
                            value = stringResource(id = R.string.cancel),
                            onClick = {
                                viewModel.onEvent(RegisterVehicleUIEvent.CancelRegisterVehicleButtonClicked)
                                navigateToScreen(RmcScreen.MyAccount.name)
                            }
                        )
                    }
                }
                val context = LocalContext.current
                val toastMessage = if (uiState.vehicleUpdated) {
                    stringResource(R.string.vehicle_registered_successfully)
                } else {
                    stringResource(R.string.unable_to_register_vehicle)
                }
                if (uiState.vehicleUpdated) {
                    Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
                    navigateToScreen(RmcScreen.MyVehicles.name)
                    viewModel.onEvent(RegisterVehicleUIEvent.ResetVehicleUpdated)
                }
            }
        }
    }
}

@Composable
fun AddressEdit(
    addressItem: AddressItem,
    modifier: Modifier,
    addressPlaceItemPredictions: List<PlaceItem>,
    onQueryChanged: (String) -> Unit,
    onClearClick: () -> Unit,
    onDoneClick: () -> Unit,
    onItemClick: (PlaceItem) -> Unit
) {

    Column(
        modifier = modifier.padding(top = 8.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.Center
    ) {

        AutoCompleteUI(
            modifier = Modifier.fillMaxWidth(),
            query = addressItem.streetAddress,
            queryLabel = stringResource(id = R.string.vehicle_location),
            useOutlined = true,
            onQueryChanged = onQueryChanged,
            predictions = addressPlaceItemPredictions,
            onClearClick = onClearClick,
            onDoneActionClick = onDoneClick,
            onItemClick = onItemClick
        ) {
            Text(text = it.address, style = MaterialTheme.typography.bodyLarge)
        }


        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.padding(horizontal = 8.dp)) {

            Row(modifier = modifier.height(IntrinsicSize.Min)) {

                Column(modifier = Modifier.weight(1f)) {

                    Text("City", fontWeight = FontWeight.Bold)

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        addressItem.city,
                        modifier = Modifier.weight(1.0f)
                    )
                }



                Column(modifier = Modifier.weight(1f)) {

                    Text("State", fontWeight = FontWeight.Bold)


                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        addressItem.state,
                        modifier = Modifier.weight(1.0f)
                    )
                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = modifier.height(IntrinsicSize.Min)) {

                Column(modifier = Modifier.weight(1f)) {
                    Text("Postal Code", fontWeight = FontWeight.Bold)

                    Text(
                        addressItem.postalCode,
                        modifier = Modifier.weight(1.0f)
                    )
                }


                Column(modifier = Modifier.weight(1f)) {
                    Text("Country", fontWeight = FontWeight.Bold)

                    Text(
                        addressItem.country,
                        modifier = Modifier.weight(1.0f)
                    )

                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = modifier.height(IntrinsicSize.Min)) {


                Column(modifier = Modifier.weight(1f)) {

                    Text("Latitude", fontWeight = FontWeight.Bold)



                    Text(
                        addressItem.latitude.toString(),
                        modifier = Modifier.weight(1.0f)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Column(modifier = Modifier.weight(1f)) {

                    Text("Longitude", fontWeight = FontWeight.Bold)

                    Text(
                        addressItem.longitude.toString(),
                        modifier = Modifier.weight(1.0f)
                    )
                }

            }

        }
    }

}