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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.components.RmcFilledIconButton
import com.digitalarchitects.rmc_app.components.RmcFilledTonalIconButton
import com.digitalarchitects.rmc_app.components.RmcFloatingActionButton
import com.digitalarchitects.rmc_app.components.RmcImgFilledIconButton
import com.digitalarchitects.rmc_app.components.RmcListItemDivider
import com.digitalarchitects.rmc_app.components.RmcMapVehicleCluster
import com.digitalarchitects.rmc_app.components.RmcMapVehicleItem
import com.digitalarchitects.rmc_app.components.RmcVehicleListItem
import com.digitalarchitects.rmc_app.data.rentacar.RentACarViewModel
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
import kotlinx.coroutines.launch

val startLocation = LatLng(51.583698, 4.797110)
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

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(startLocation, 13f)
    }

    BottomSheetScaffold(
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = dimensionResource(R.dimen.padding_large))
            ) {
                Text(text = "Vehicle info here")
            }
        },
        sheetPeekHeight = 0.dp,
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
                        cameraPositionState = cameraPositionState
                    )
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
                                rentACarViewModel.viewListButtonClicked()
                            }
                        )
                    }
                }
                // Bottom sheet #2
                val sheetState = rememberModalBottomSheetState()
                val scope = rememberCoroutineScope()

                if (rentACarUIState.showListViewSheet) {
                    ModalBottomSheet(
                        onDismissRequest = { rentACarViewModel.viewListButtonClicked() },
                        sheetState = sheetState,
                    ) {
                        Column(
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                        ) {
                            repeat(2) {
                                rentACarViewModel.listOfVehicles.forEach { vehicle ->
                                    RmcVehicleListItem(
                                        vehicle,
                                        onClick = {
                                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                                rentACarViewModel.viewListButtonClicked()
                                            }
                                        }
                                    )
                                    RmcListItemDivider()
                                }
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
    cameraPositionState: CameraPositionState
) {
    val items = remember { mutableStateListOf<RmcVehicleItem>() }
    LaunchedEffect(Unit) {
        items.add(
            RmcVehicleItem(
                LatLng(51.583698, 4.797110),
                "Avans Hogeschool",
                "Hogeschoollaan 1",
                0f
            )
        )
        items.add(
            RmcVehicleItem(
                LatLng(51.585720, 4.793230),
                "Avans Hogeschool",
                "Lovensdijkstraat 61",
                0f
            )
        )
        items.add(RmcVehicleItem(LatLng(51.58656, 4.77596), "Avans Hogeschool", "Bijster 7-21", 0f))
        items.add(
            RmcVehicleItem(
                LatLng(51.5794365, 4.810962),
                "Avans Hogeschool",
                "Beukenlaan 1",
                0f
            )
        )
        items.add(RmcVehicleItem(LatLng(51.4698905, 5.5466656), "Home", "where the heart is", 0f))
    }
    RmcMap(items = items, cameraPositionState)
}

@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun RmcMap(
    items: List<RmcVehicleItem>,
    cameraPositionState: CameraPositionState
) {
    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                mapType = MapType.NORMAL,
                maxZoomPreference = 18f,
                minZoomPreference = 11f
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
            clusterManager.setOnClusterClickListener {
                Log.d(TAG, "Cluster clicked! $it")
                false
            }
            clusterManager.setOnClusterItemClickListener {
                Log.d(TAG, "Cluster item clicked! $it")
                false
            }
            clusterManager.setOnClusterItemInfoWindowClickListener {
                Log.d(TAG, "Cluster item info window clicked! $it")
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

data class RmcVehicleItem(
    val vehiclePosition: LatLng,
    val vehicleTitle: String,
    val vehicleSnippet: String,
    val vehicleZIndex: Float,
) : ClusterItem {
    override fun getPosition(): LatLng =
        vehiclePosition

    override fun getTitle(): String =
        vehicleTitle

    override fun getSnippet(): String =
        vehicleSnippet

    override fun getZIndex(): Float =
        vehicleZIndex
}