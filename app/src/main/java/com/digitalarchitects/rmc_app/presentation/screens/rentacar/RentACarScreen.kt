package com.digitalarchitects.rmc_app.presentation.screens.rentacar

import android.Manifest
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialogDefaults
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
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.domain.util.hasLocationPermission
import com.digitalarchitects.rmc_app.presentation.RmcScreen
import com.digitalarchitects.rmc_app.presentation.components.RmcDivider
import com.digitalarchitects.rmc_app.presentation.components.RmcFilledButton
import com.digitalarchitects.rmc_app.presentation.components.RmcFilledIconButton
import com.digitalarchitects.rmc_app.presentation.components.RmcFilledTonalButton
import com.digitalarchitects.rmc_app.presentation.components.RmcFilledTonalIconButton
import com.digitalarchitects.rmc_app.presentation.components.RmcFloatingActionButton
import com.digitalarchitects.rmc_app.presentation.components.RmcImgFilledIconButton
import com.digitalarchitects.rmc_app.presentation.components.RmcMapVehicleCluster
import com.digitalarchitects.rmc_app.presentation.components.RmcMapVehicleItem
import com.digitalarchitects.rmc_app.presentation.components.RmcSpacer
import com.digitalarchitects.rmc_app.presentation.components.RmcTextField
import com.digitalarchitects.rmc_app.presentation.components.RmcVehicleDetails
import com.digitalarchitects.rmc_app.presentation.components.RmcVehicleListItem
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.clustering.Clustering
import com.google.maps.android.compose.clustering.rememberClusterManager
import com.google.maps.android.compose.clustering.rememberClusterRenderer
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(
    ExperimentalMaterial3Api::class,
    MapsComposeExperimentalApi::class,
    ExperimentalPermissionsApi::class
)
@Composable
fun RentACarScreen(
    viewModel: RentACarViewModel,
    navigateToScreen: (String) -> Unit
) {
    // UI States
    val rentACarUiState by viewModel.rentACarUiState.collectAsState()
    val locationPermissionsUiState by viewModel.locationPermissionsUiState.collectAsStateWithLifecycle()

    // Set scope and context
    val scope = rememberCoroutineScope()
    val context: Context = LocalContext.current

    // Set Maps and bottom sheets states
    val cameraState = rememberCameraPositionState {
        if (rentACarUiState.userLocation == null) {
            position = CameraPosition.fromLatLngZoom(
                rentACarUiState.cameraPosition,
                rentACarUiState.zoomLevel
            )
        }
    }
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

    // Location permissions
    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    when {
        permissionState.allPermissionsGranted -> {
            LaunchedEffect(Unit) {
                viewModel.onEvent(RentACarUIEvent.PermissionsGranted)
            }
        }

        permissionState.shouldShowRationale -> {
            if (rentACarUiState.showRationaleDialog) {
                RmcPermissionDialog(
                    onDismiss = {
                        viewModel.onEvent(RentACarUIEvent.PermissionsRevoked)
                        viewModel.onEvent(RentACarUIEvent.ShowPermissionDialog(false))
                    },
                    onConfirm = { permissionState.launchMultiplePermissionRequest() }
                )
            }
        }

        !permissionState.allPermissionsGranted && !permissionState.shouldShowRationale -> {
            LaunchedEffect(Unit) {
                viewModel.onEvent(RentACarUIEvent.PermissionsRevoked)
            }
        }
    }

    with(locationPermissionsUiState) {
        when (this) {
            is LocationPermissionsUIState.GrantedPermissions -> {
                rentACarUiState.userLocation =
                    LatLng(
                        this.location?.latitude ?: 0.0,
                        this.location?.longitude ?: 0.0
                    )
            }

            else -> {}
        }
    }

    LaunchedEffect(Unit) {
        snapshotFlow { cameraState.isMoving }
            .collect {
                viewModel.onEvent(RentACarUIEvent.ZoomLevelChanged(cameraState.position.zoom))
                viewModel.onEvent(RentACarUIEvent.CameraPositionChanged(cameraState.position.target))
            }
    }

    // Start Rent A Car screen
    BottomSheetScaffold(
        scaffoldState = detailsBottomSheet,
        sheetPeekHeight = 194.dp, // 324.dp with image

        // Bottom sheet: Vehicle details
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (rentACarUiState.activeVehicleId != null) {
                    RmcVehicleDetails(
                        rentACarUiState.listOfVehicles.first { vehicle ->
                            vehicle.vehicleId == rentACarUiState.activeVehicleId
                        },
                        showAvailability = false
                    )
                }
                RmcDivider()
                RmcRentCarForm(
                    value = rentACarUiState.date,
                    enabled = !rentACarUiState.placingReservation,
                    onValueChange = { date ->
                        viewModel.onEvent(RentACarUIEvent.DateChanged(date))
                    },
                    onReserveButtonClicked = {
                        viewModel.onEvent(RentACarUIEvent.ReserveVehicleButtonClicked)
                        navigateToScreen(RmcScreen.MyRentals.name)
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
                        cameraPositionState = cameraState,
                        properties = MapProperties(
                            isMyLocationEnabled = false,
                            mapType = MapType.NORMAL,
                            maxZoomPreference = 18f,
                            minZoomPreference = 8f,
                            isTrafficEnabled = false
                        ),
                        uiSettings = MapUiSettings(
                            zoomControlsEnabled = false,
                            zoomGesturesEnabled = true,
                            tiltGesturesEnabled = false
                        ),
                        onMapClick = { location ->
                            Log.d(TAG, "On map clicked: $location")
                            scope.launch { detailsBottomSheet.bottomSheetState.hide() }
                        }
                    ) {
                        // Draw marker if user location is set
                        if (rentACarUiState.userLocation != null) {
                            Marker(
                                state = MarkerState(position = rentACarUiState.userLocation!!),
                                title = stringResource(R.string.your_location),
                                draggable = false
                            )
                        }
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
                                scope.launch {
                                    cameraState.centerOnLocation(clusterItem.position, 12f)
                                }
                                false
                            }
                            clusterManager.setOnClusterItemClickListener { vehicleItem ->
                                Log.d(TAG, "Item clicked: $vehicleItem")
                                viewModel.onEvent(
                                    RentACarUIEvent.RmcMapVehicleItemClicked(vehicleItem.getId())
                                )
                                scope.launch {
                                    detailsBottomSheet.bottomSheetState.partialExpand()
                                    cameraState.centerOnLocation(vehicleItem.position)
                                }
                                false
                            }
                            clusterManager.setOnClusterItemInfoWindowClickListener { vehicleItem ->
                                Log.d(TAG, "Item info window clicked: $vehicleItem")
                                scope.launch {
                                    detailsBottomSheet.bottomSheetState.expand()
                                }
                            }
                        }
                        SideEffect {
                            if (clusterManager?.renderer != renderer) {
                                clusterManager?.renderer = renderer ?: return@SideEffect
                            }
                        }
                        if (clusterManager != null) {
                            Clustering(
                                items = rentACarUiState.vehicleMapItems,
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
                        if (!context.hasLocationPermission()) {
                            RmcFilledTonalIconButton(
                                icon = Icons.Filled.MyLocation,
                                label = R.string.my_location,
                                onClick = {
                                    permissionState.launchMultiplePermissionRequest()
                                    viewModel.onEvent(RentACarUIEvent.ShowPermissionDialog(true))
                                },
                                modifier = Modifier.padding(
                                    bottom = dimensionResource(R.dimen.padding_medium)
                                )
                            )
                        } else {
                            RmcFilledIconButton(
                                icon = Icons.Filled.MyLocation,
                                label = R.string.my_location,
                                enabled = rentACarUiState.userLocation != null,
                                onClick = {
                                    scope.launch {
                                        cameraState.centerOnLocation(rentACarUiState.userLocation!!)
                                    }
                                },
                                modifier = Modifier.padding(
                                    bottom = dimensionResource(R.dimen.padding_medium)
                                )
                            )
                        }
                        RmcFloatingActionButton(
                            icon = Icons.Filled.List,
                            label = R.string.list_view,
                            onClick = {
                                viewModel.onEvent(RentACarUIEvent.ShowListView(true))
                            }
                        )
                    }
                }

                // Bottom sheet: Vehicle list view
                if (rentACarUiState.showVehicleList) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            viewModel.onEvent(RentACarUIEvent.ShowListView(false))
                        },
                        sheetState = listBottomSheet,
                    ) {
                        LazyColumn {
                            itemsIndexed(rentACarUiState.listOfVehicles) { index, vehicle ->
                                RmcVehicleListItem(vehicle) { vehicleId ->
                                    scope.launch {
                                        cameraState.centerOnLocation(
                                            LatLng(
                                                vehicle.latitude.toDouble(),
                                                vehicle.longitude.toDouble()
                                            )
                                        )
                                    }
                                    scope.launch { listBottomSheet.hide() }.invokeOnCompletion {
                                        viewModel.onEvent(
                                            RentACarUIEvent.RmcMapVehicleItemClicked(id = vehicleId)
                                        )
                                    }
                                    scope.launch {
                                        delay(400L)
                                        detailsBottomSheet.bottomSheetState.partialExpand()
                                    }
                                }
                                if (index < rentACarUiState.listOfVehicles.lastIndex)
                                    RmcDivider()
                            }
                        }
                        RmcSpacer()
                    }
                }
            }
        }
    }
}

// Dialog to explain required permissions
@Composable
fun RmcPermissionDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        )
    ) {
        androidx.compose.material3.Surface(
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation,
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
        ) {
            Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_large))) {
                Text(text = stringResource(R.string.location_permissions))
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
                ) {
                    Column(Modifier.weight(1f)) {
                        RmcFilledTonalButton(
                            value = stringResource(id = R.string.dismiss),
                            onClick = onDismiss
                        )
                    }
                    Column(Modifier.weight(1f)) {
                        RmcFilledButton(
                            value = stringResource(id = R.string.approve),
                            onClick = onConfirm
                        )
                    }
                }
            }
        }
    }
}

// Form to reserve a vehicle
@Composable
fun RmcRentCarForm(
    value: String,
    enabled: Boolean,
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
            value = value,
            enabled = enabled,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Send
            ),
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        RmcSpacer(16)
        RmcFilledButton(
            value = stringResource(id = R.string.reserve),
            icon = Icons.Filled.Key,
            isEnabled = enabled,
            onClick = { onReserveButtonClicked() }
        )
    }
}

// Center camera position on location
private suspend fun CameraPositionState.centerOnLocation(
    location: LatLng,
    zoom: Float = 15f
) = animate(
    update = CameraUpdateFactory.newLatLngZoom(
        location,
        zoom
    ),
    durationMs = 1000
)