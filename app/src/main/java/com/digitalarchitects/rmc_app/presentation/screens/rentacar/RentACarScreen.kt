package com.digitalarchitects.rmc_app.presentation.screens.rentacar

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.presentation.RmcScreen
import com.digitalarchitects.rmc_app.presentation.components.RmcDivider
import com.digitalarchitects.rmc_app.presentation.components.RmcFilledButton
import com.digitalarchitects.rmc_app.presentation.components.RmcFilledIconButton
import com.digitalarchitects.rmc_app.presentation.components.RmcFilledTonalIconButton
import com.digitalarchitects.rmc_app.presentation.components.RmcFloatingActionButton
import com.digitalarchitects.rmc_app.presentation.components.RmcImgFilledIconButton
import com.digitalarchitects.rmc_app.presentation.components.RmcMapVehicleCluster
import com.digitalarchitects.rmc_app.presentation.components.RmcMapVehicleItem
import com.digitalarchitects.rmc_app.presentation.components.RmcSpacer
import com.digitalarchitects.rmc_app.presentation.components.RmcTextField
import com.digitalarchitects.rmc_app.presentation.components.RmcVehicleDetails
import com.digitalarchitects.rmc_app.presentation.components.RmcVehicleListItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.clustering.Clustering
import com.google.maps.android.compose.clustering.rememberClusterManager
import com.google.maps.android.compose.clustering.rememberClusterRenderer
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, MapsComposeExperimentalApi::class)
@Composable
fun RentACarScreen(
    viewModel: RentACarViewModel,
    navigateToScreen: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    // Set CoroutineScope
    val scope = rememberCoroutineScope()

    // Set Google Maps camera position
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(uiState.startLocation, 10f)
    }

    // Set Vehicle details and List view bottom sheet state
    val detailsBottomSheet = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false,
        )
    )
    val listBottomSheet = rememberModalBottomSheetState()

    // Get vehicles and create map items in view model
    LaunchedEffect(Unit) {
        viewModel.setMapData()
    }

    // Start Rent A Car screen
    BottomSheetScaffold(
        scaffoldState = detailsBottomSheet,
        sheetPeekHeight = 324.dp,

        // Bottom sheet: Vehicle details
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (uiState.activeVehicleId != null) {
                    RmcVehicleDetails(
                        uiState.listOfVehicles.first { it.vehicleId == uiState.activeVehicleId },
                        showAvailability = false
                    )
                }
                RmcDivider()
                RmcRentCarForm(
                    onValueChange = {
                        // rentACarUIState: Add date for rental
                    },
                    onReserveButtonClicked = {
                        // rentACarViewModel: Add event for Reserve car
                    }
                )
                RmcSpacer()
            }
        },
    ) {
        Surface(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxSize(),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(Modifier.fillMaxSize()) {

                    // Start Google Maps
                    GoogleMap(
                        googleMapOptionsFactory = {
                            GoogleMapOptions().mapId("DEMO_MAP_ID")
                        },
                        cameraPositionState = cameraPositionState,
                        onMapClick = { location ->
                            Log.d(TAG, "On map clicked: $location")
                            scope.launch { detailsBottomSheet.bottomSheetState.hide() }
                        }

                    ) {
                        // Set map properties & settings
                        MapProperties(
                            mapType = MapType.NORMAL,
                            maxZoomPreference = 18f,
                            minZoomPreference = 8f
                        )
                        MapUiSettings(
                            compassEnabled = false,
                            mapToolbarEnabled = false,
                            zoomControlsEnabled = true
                        )

                        // Set cluster manager and callback functions
                        val clusterManager = rememberClusterManager<VehicleMapItem>()
                        val renderer = rememberClusterRenderer(
                            clusterContent = { cluster ->
                                RmcMapVehicleCluster(number = cluster.size)
                            },
                            clusterItemContent = {
                                RmcMapVehicleItem()
                            },
                            clusterManager = clusterManager,
                        )
                        SideEffect {
                            clusterManager ?: return@SideEffect
                            clusterManager.setOnClusterClickListener { clusterItem ->
                                Log.d(TAG, "Cluster clicked: $clusterItem")
                                cameraPositionState.move(CameraUpdateFactory.zoomTo(12f))
                                false
                            }
                            clusterManager.setOnClusterItemClickListener { vehicleItem ->
                                Log.d(TAG, "Item clicked: $vehicleItem")
                                viewModel.onEvent(
                                    RentACarUIEvent.RmcMapVehicleItemClicked(
                                        vehicleItem.getId()
                                    )
                                )
                                scope.launch {
                                    detailsBottomSheet.bottomSheetState.partialExpand()
                                }
                                cameraPositionState.move(CameraUpdateFactory.zoomTo(14f))
                                false
                            }
                            clusterManager.setOnClusterItemInfoWindowClickListener { vehicleItem ->
                                Log.d(TAG, "Cluster item info window clicked: $vehicleItem")
                            }
                        }
                        SideEffect {
                            if (clusterManager?.renderer != renderer) {
                                clusterManager?.renderer = renderer ?: return@SideEffect
                            }
                        }
                        if (clusterManager != null) {
                            Clustering(
                                items = uiState.vehicleMapItems,
                                clusterManager = clusterManager,
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                // AppBar: RMC
                Row {
                    Row(
                        modifier = Modifier
                            .padding(dimensionResource(R.dimen.padding_small))
                    ) {
                        RmcFilledIconButton(
                            icon = Icons.Filled.Search,
                            label = R.string.search,
                            onClick = { navigateToScreen(RmcScreen.Search.name) },
                            modifier = Modifier.padding(
                                horizontal = dimensionResource(R.dimen.padding_extra_small)
                            )
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(dimensionResource(R.dimen.padding_small))
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                    ) {
                        RmcFilledTonalIconButton(
                            icon = Icons.Filled.Key,
                            label = R.string.rent_my_car,
                            onClick = { navigateToScreen(RmcScreen.RentOutMyCar.name) },
                        )
                        RmcFilledTonalIconButton(
                            icon = Icons.Filled.CarRental,
                            label = R.string.my_rentals,
                            onClick = { navigateToScreen(RmcScreen.MyRentals.name) },
                            modifier = Modifier.padding(
                                horizontal = dimensionResource(R.dimen.padding_extra_small)
                            )
                        )
                        RmcImgFilledIconButton(
                            image = R.drawable.civic,
                            label = R.string.my_rentals,
                            onClick = { navigateToScreen("MyAccount") },
                            modifier = Modifier.padding(
                                end = dimensionResource(R.dimen.padding_extra_small)
                            )
                        )
                    }
                }

                // FAB: View list
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            end = dimensionResource(R.dimen.padding_medium),
                            bottom = dimensionResource(R.dimen.padding_medium)
                        ),
                    horizontalArrangement = Arrangement.End
                ) {
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        RmcFilledIconButton(
                            icon = Icons.Filled.MyLocation,
                            label = R.string.my_location,
                            onClick = {
                                cameraPositionState.move(
                                    CameraUpdateFactory.newLatLng(
                                        uiState.userLocation
                                    )
                                )
                            },
                            modifier = Modifier.padding(
                                bottom = dimensionResource(R.dimen.padding_medium)
                            )
                        )
                        RmcFloatingActionButton(
                            icon = Icons.Filled.List,
                            label = R.string.view_list,
                            onClick = {
                                viewModel.onEvent(RentACarUIEvent.ShowListView(true))
                            }
                        )
                    }
                }

                // Bottom sheet: Vehicle list view
                if (uiState.showVehicleList) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            viewModel.onEvent(
                                RentACarUIEvent.ShowListView(
                                    false
                                )
                            )
                        },
                        sheetState = listBottomSheet,
                    ) {
                        Column(
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                        ) {
                            uiState.listOfVehicles.forEach { vehicle ->
                                RmcVehicleListItem(vehicle) { vehicleId ->
                                    scope.launch { listBottomSheet.hide() }.invokeOnCompletion {
                                        viewModel.onEvent(
                                            RentACarUIEvent.RmcMapVehicleItemClicked(id = vehicleId)
                                        )
                                    }
                                    cameraPositionState.move(
                                        CameraUpdateFactory.newLatLng(
                                            LatLng(
                                                vehicle.latitude.toDouble(),
                                                vehicle.longitude.toDouble()
                                            )
                                        )
                                    )
                                    scope.launch {
                                        delay(400L)
                                        detailsBottomSheet.bottomSheetState.partialExpand()
                                    }
                                }
                                RmcDivider()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RmcRentCarForm(
    onValueChange: (String) -> Unit,
    onReserveButtonClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_large))
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = dimensionResource(R.dimen.padding_small)),
            text = stringResource(R.string.rent_car),
            style = MaterialTheme.typography.titleMedium,
        )
        RmcTextField(
            label = stringResource(id = R.string.date),
            icon = Icons.Filled.CalendarMonth,
            value = "03-12-2023",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        RmcSpacer(16)
        RmcFilledButton(
            value = stringResource(id = R.string.reserve),
            icon = Icons.Filled.Key,
            onClick = { onReserveButtonClicked() }
        )
    }
}

@Preview
@Composable
fun RentACarScreenPreview() {
    RentACarScreen(
        viewModel = viewModel(),
        navigateToScreen = { }
    )
}