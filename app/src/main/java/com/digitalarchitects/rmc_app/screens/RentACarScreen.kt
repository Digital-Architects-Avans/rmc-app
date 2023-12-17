package com.digitalarchitects.rmc_app.screens

import android.graphics.Color
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.components.RmcFilledIconButton
import com.digitalarchitects.rmc_app.components.RmcFilledTonalIconButton
import com.digitalarchitects.rmc_app.components.RmcFloatingActionButton
import com.digitalarchitects.rmc_app.components.RmcImgFilledIconButton
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PinConfig
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RentACarScreen(
    onSearchButtonClicked: () -> Unit,
    onRentMyCarButtonClicked: () -> Unit,
    onMyRentalsButtonClicked: () -> Unit,
    onMyAccountButtonClicked: () -> Unit,
) {
    // Setup for GoogleMap composable
    val startLocation = LatLng(51.583698, 4.797110)
    val userLocation = LatLng(51.469890, 5.546670)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(startLocation, 13f)
    }

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
                zoomControlsEnabled = false
            )
        )
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
                    RmcMap(
                        properties = mapProperties,
                        uiSettings = mapUiSettings,
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
                            onClick = { /* TODO*/ }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RmcMap(
    properties: MapProperties,
    uiSettings: MapUiSettings,
    cameraPositionState: CameraPositionState
) {
    GoogleMap(
        googleMapOptionsFactory = {
            GoogleMapOptions().mapId("DEMO_MAP_ID")
        },
        properties = properties,
        uiSettings = uiSettings,
        cameraPositionState = cameraPositionState
    ) {
        // Setup pinConfig RMC
        val glyphImage: Int = R.drawable.ic_map_marker
        val descriptor = BitmapDescriptorFactory.fromResource(glyphImage)

        val pinConfig = PinConfig.builder()
            .setGlyph(PinConfig.Glyph(descriptor))
            .setBackgroundColor(MaterialTheme.colorScheme.primary.toArgb())
            .setBorderColor(Color.WHITE)
            .build()

        // Placing test marker
        AdvancedMarker(
            state = MarkerState(position = LatLng(51.583698, 4.797110)),
            title = "Avans Hogeschool",
            snippet = "Hogeschoollaan 1",
            pinConfig = pinConfig
        )
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