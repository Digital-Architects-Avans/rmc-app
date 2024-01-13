package com.digitalarchitects.rmc_app.presentation.screens.myvehicles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.presentation.RmcScreen
import com.digitalarchitects.rmc_app.presentation.components.RmcAppBar
import com.digitalarchitects.rmc_app.presentation.components.RmcDivider
import com.digitalarchitects.rmc_app.presentation.components.RmcFilledButton
import com.digitalarchitects.rmc_app.presentation.components.RmcFilledTonalButton
import com.digitalarchitects.rmc_app.presentation.components.RmcFloatingActionButton
import com.digitalarchitects.rmc_app.presentation.components.RmcSpacer
import com.digitalarchitects.rmc_app.presentation.components.RmcVehicleDetails
import com.digitalarchitects.rmc_app.presentation.components.RmcVehicleListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyVehiclesScreen(
    viewModel: MyVehiclesViewModel,
    navigateToScreen: (String) -> Unit,
    navigateToEditVehicle: (String, String?) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    val vehicleBottomSheet = rememberModalBottomSheetState()

    LaunchedEffect(uiState.listOfVehicles) {
        viewModel.onEvent(MyVehiclesUIEvent.FetchVehicles)
    }

    Scaffold(
        topBar = {
            RmcAppBar(
                title = R.string.screen_title_my_vehicles,
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                navigateUp = {
                    navigateToScreen(RmcScreen.MyAccount.name)
                },
            )
        },
        floatingActionButton = {
            RmcFloatingActionButton(
                icon = Icons.Default.Add,
                label = R.string.add_vehicle,
                onClick = {
                    navigateToScreen(RmcScreen.RegisterVehicle.name)
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
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                if (!uiState.isLoading) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        itemsIndexed(uiState.listOfVehicles) { index, vehicle ->

                            // Format the address to drop the country and postal code
                            val addressAsList = vehicle.address.split(",")
                            val streetAndCity = addressAsList[0] + ", " + addressAsList[1].drop(8)

                            RmcVehicleListItem(
                                vehicle = vehicle,
                                ownerView = true,
                                onClick = {
                                    viewModel.onEvent(MyVehiclesUIEvent.ShowVehicleDetails(vehicle.vehicleId))
                                }
                            )

                            if (index < uiState.listOfVehicles.lastIndex)
                                RmcDivider()
                        }
                    }
                } else {
                    // Show loading indicator or any other UI when isLoading is true
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.Center)
                    )
                }

                // Display vehicle details in a modal bottom sheet when a vehicle is selected
                uiState.selectedVehicle?.let { vehicle ->
                    ModalBottomSheet(
                        sheetState = vehicleBottomSheet,
                        onDismissRequest = { viewModel.onEvent(MyVehiclesUIEvent.CancelShowVehicleDetails) },
                    ) {
                        RmcVehicleDetails(
                            vehicle = vehicle,
                            isAvailable = uiState.isAvailable,
                            ownerView = true
                        )
                        RmcDivider()
                        RmcOwnerCarForm(
                            isAvailable = uiState.isAvailable,
                            onAvailabilityChanged = {
                                viewModel.onEvent(MyVehiclesUIEvent.ChangeAvailability(vehicle.vehicleId))
                            },
                            onDeleteClick = {
                                viewModel.onEvent(MyVehiclesUIEvent.DeleteVehicle(vehicle.vehicleId))
                                viewModel.onEvent(MyVehiclesUIEvent.CancelShowVehicleDetails)
                            },
                            onEditClick = {
                                navigateToEditVehicle(
                                    RmcScreen.EditMyVehicle.name,
                                    vehicle.vehicleId
                                )
                                viewModel.onEvent(MyVehiclesUIEvent.CancelShowVehicleDetails)
                            }
                        )
                        RmcSpacer(32)
                    }
                }
            }
        }
    }
}

// Form for owner to manage vehicle
@Composable
fun RmcOwnerCarForm(
    isAvailable: Boolean,
    onAvailabilityChanged: () -> Unit,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_large))
    ) {
        // Vehicle_availability switch
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.vehicle_availability))
            Switch(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End),
                checked = isAvailable,
                onCheckedChange = { onAvailabilityChanged() }
            )
        }

        RmcSpacer(16)

        // Delete and Edit buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
        ) {
            Column(Modifier.weight(1f)) {
                RmcFilledTonalButton(
                    value = stringResource(id = R.string.delete_vehicle),
                    onClick = {
                        onDeleteClick()
                    }
                )
            }
            Column(Modifier.weight(1f)) {
                RmcFilledButton(
                    value = stringResource(id = R.string.edit_vehicle),
                    onClick = {
                        onEditClick()
                    }
                )
            }
        }
    }
}