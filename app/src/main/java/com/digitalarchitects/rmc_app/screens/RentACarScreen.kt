package com.digitalarchitects.rmc_app.screens

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.components.RmcDivider
import com.digitalarchitects.rmc_app.components.RmcFilledButton
import com.digitalarchitects.rmc_app.components.RmcFilledIconButton
import com.digitalarchitects.rmc_app.components.RmcFilledTonalIconButton
import com.digitalarchitects.rmc_app.components.RmcFloatingActionButton
import com.digitalarchitects.rmc_app.components.RmcImgFilledIconButton
import com.digitalarchitects.rmc_app.components.RmcMapVehicleCluster
import com.digitalarchitects.rmc_app.components.RmcMapVehicleItem
import com.digitalarchitects.rmc_app.components.RmcSpacer
import com.digitalarchitects.rmc_app.components.RmcTextField
import com.digitalarchitects.rmc_app.components.RmcVehicleDetails
import com.digitalarchitects.rmc_app.components.RmcVehicleListItem
import com.digitalarchitects.rmc_app.data.rentacar.RentACarUIEvent
import com.digitalarchitects.rmc_app.data.rentacar.RentACarViewModel
import com.digitalarchitects.rmc_app.model.Vehicle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.compose.CameraPositionState
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

val startLocation = LatLng(51.587959, 4.775130)
val userLocation = LatLng(51.469890, 5.546670)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RentACarScreen(
    rentACarViewModel: RentACarViewModel = viewModel(),
    onSearchButtonClicked: () -> Unit,
    onRentMyCarButtonClicked: () -> Unit,
    onMyRentalsButtonClicked: () -> Unit,
    onMyAccountButtonClicked: () -> Unit,
) {
    val rentACarUIState by rentACarViewModel.uiState.collectAsState()
    val vehicleList = rentACarViewModel.listOfVehicles

    val scope = rememberCoroutineScope()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(startLocation, 11f)
    }

    val detailsBottomSheet = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(
            initialValue = SheetValue.Hidden,
            skipPartiallyExpanded = false,
            skipHiddenState = false
        )
    )

    BottomSheetScaffold(
        scaffoldState = detailsBottomSheet,
        sheetPeekHeight = 324.dp,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                RmcVehicleDetails(
                    vehicleList[rentACarUIState.detailsVehicleId],
                    showAvailability = false
                )
                RmcDivider()

                RmcRentCarForm(
                    onReserveButtonClicked = {
                        // rentACarViewModel: Add event for Reserve car
                    },
                    onValueChange = {
                        // rentACarUIState: Add date for rental
                    }
                )
            }
        },
    ) {
        Surface(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .background(
                        brush = Brush.linearGradient(
                            listOf(
                                MaterialTheme.colorScheme.secondaryContainer,
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.secondary
                            )
                        ),
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(Modifier.fillMaxSize()) {
                    RmcMapClustering(
                        cameraPositionState = cameraPositionState,
                        vehicleList = vehicleList
                    ) { vehicleId ->
                        rentACarViewModel.onEvent(RentACarUIEvent.RmcMapVehicleItemClicked(id = vehicleId))
                        scope.launch { detailsBottomSheet.bottomSheetState.partialExpand() }
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
                            onClick = onSearchButtonClicked,
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
                            label = R.string.rent_out_my_car,
                            onClick = onRentMyCarButtonClicked,
                        )
                        RmcFilledTonalIconButton(
                            icon = Icons.Filled.CarRental,
                            label = R.string.my_rentals,
                            onClick = onMyRentalsButtonClicked,
                            modifier = Modifier.padding(
                                horizontal = dimensionResource(R.dimen.padding_extra_small)
                            )
                        )
                        RmcImgFilledIconButton(
                            image = R.drawable.civic,
                            label = R.string.my_rentals,
                            onClick = onMyAccountButtonClicked,
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
                                cameraPositionState.move(CameraUpdateFactory.newLatLng(userLocation))
                            },
                            modifier = Modifier.padding(
                                bottom = dimensionResource(R.dimen.padding_medium)
                            )
                        )
                        RmcFloatingActionButton(
                            icon = Icons.Filled.List,
                            label = R.string.view_list,
                            onClick = {
                                rentACarViewModel.onEvent(RentACarUIEvent.ShowListView(true))
                            }
                        )
                    }
                }
                // Bottom sheet #2
                val listBottomSheet = rememberModalBottomSheetState()

                if (rentACarUIState.showVehicleList) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            rentACarViewModel.onEvent(
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
                            vehicleList.forEach { vehicle ->
                                RmcVehicleListItem(
                                    vehicle,
                                    onClick = { vehicleId ->
                                        scope.launch { listBottomSheet.hide() }.invokeOnCompletion {
                                            rentACarViewModel.onEvent(
                                                RentACarUIEvent.RmcMapVehicleItemClicked(id = vehicleId)
                                            )
                                        }
                                        scope.launch {
                                            delay(400L)
                                            detailsBottomSheet.bottomSheetState.partialExpand()
                                        }
                                    }
                                )
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
fun RmcMapClustering(
    cameraPositionState: CameraPositionState,
    vehicleList: List<Vehicle>,
    showDetailsView: (Int) -> Unit
) {
    val items = remember { mutableStateListOf<RmcVehicleItem>() }
    LaunchedEffect(Unit) {
        vehicleList.forEach { vehicle ->
            items.add(
                RmcVehicleItem(
                    vehicleId = vehicle.id,
                    LatLng(vehicle.latitude.toDouble(), vehicle.longitude.toDouble()),
                    vehicleSnippet = "${vehicle.year} - ${vehicle.brand} ${vehicle.model}",
                    vehicleTitle = vehicle.licensePlate,
                    vehicleZIndex = 0f
                )
            )
        }
    }
    RmcMap(items = items, cameraPositionState, showDetailsView)
}

@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun RmcMap(
    items: List<RmcVehicleItem>,
    cameraPositionState: CameraPositionState,
    showDetailsView: (Int) -> Unit,
) {
    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                mapType = MapType.NORMAL,
                maxZoomPreference = 18f,
                minZoomPreference = 8f
            )
        )
    }
    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                compassEnabled = false,
                mapToolbarEnabled = false,
                zoomControlsEnabled = true
            )
        )
    }

    GoogleMap(
        googleMapOptionsFactory = {
            GoogleMapOptions().mapId("DEMO_MAP_ID")
        },
        properties = mapProperties,
        uiSettings = mapUiSettings,
        cameraPositionState = cameraPositionState
    ) {
        val clusterManager = rememberClusterManager<RmcVehicleItem>()
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
                showDetailsView(vehicleItem.vehicleId)
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
                items = items,
                clusterManager = clusterManager,
            )
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

data class RmcVehicleItem(
    val vehicleId: Int,
    val vehiclePosition: LatLng,
    val vehicleTitle: String,
    val vehicleSnippet: String,
    val vehicleZIndex: Float,
) : ClusterItem {
    fun getId(): Int =
        vehicleId

    override fun getPosition(): LatLng =
        vehiclePosition

    override fun getTitle(): String =
        vehicleTitle

    override fun getSnippet(): String =
        vehicleSnippet

    override fun getZIndex(): Float =
        vehicleZIndex
}

@Preview
@Composable
fun RentACarScreenPreview() {
    RentACarScreen(
        onSearchButtonClicked = {},
        onRentMyCarButtonClicked = {},
        onMyRentalsButtonClicked = {},
        onMyAccountButtonClicked = {}
    )
}