package com.digitalarchitects.rmc_app.presentation.screens.myvehicles

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.PriceChange
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.domain.model.Vehicle
import com.digitalarchitects.rmc_app.presentation.RmcScreen
import com.digitalarchitects.rmc_app.presentation.components.RmcAppBar
import com.digitalarchitects.rmc_app.presentation.components.RmcFloatingActionButton
import com.digitalarchitects.rmc_app.presentation.components.RmcVehicleDetailsOwner

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
                navigationIcon = Icons.Rounded.Close,
                navigateUp = {
                    navigateToScreen(RmcScreen.RentACar.name)
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
                            .padding(24.dp)
                    ) {
                        itemsIndexed(uiState.listOfVehicles) { index, vehicle ->
                            VehicleListItem(
                                vehicle = vehicle,
                                location = uiState.listOfLocations[index],
                                onItemClick = {
                                    viewModel.onEvent(MyVehiclesUIEvent.ShowVehicleDetails(vehicle.vehicleId))
                                }
                            )

                            if (index < uiState.listOfVehicles.lastIndex)
                                HorizontalDivider(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 16.dp),
                                    thickness = 1.dp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
                                )
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
                    VehicleDetailsBottomSheet(
                        vehicle = vehicle,
                        location = uiState.listOfLocationsDetailed[uiState.listOfVehicles.indexOf(vehicle)],
                        sheetState = vehicleBottomSheet,
                        onDeleteClick = {
                            viewModel.onEvent(MyVehiclesUIEvent.DeleteVehicle(vehicle.vehicleId))
                            viewModel.onEvent(MyVehiclesUIEvent.CancelShowVehicleDetails)
                        },
                        onEditClick = {
                            navigateToEditVehicle(RmcScreen.EditMyVehicle.name, vehicle.vehicleId)
                            viewModel.onEvent(MyVehiclesUIEvent.CancelShowVehicleDetails)
                        },
                        onCancel = {
                            viewModel.onEvent(MyVehiclesUIEvent.CancelShowVehicleDetails)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleDetailsBottomSheet(
    vehicle: Vehicle,
    location: String,
    sheetState: SheetState,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit,
    onCancel: () -> Unit
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onCancel,
    ) {
        RmcVehicleDetailsOwner(
            vehicle = vehicle,
            location = location,
            showAvailability = true,
            onDeleteClick = { onDeleteClick() },
            onEditClick = { onEditClick() }
        )
    }
}

@Composable
fun VehicleListItem(
    vehicle: Vehicle,
    location: String,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clickable { onItemClick() }
    ) {

        Row(
            modifier = Modifier
                .height(92.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.civic),
                contentDescription = stringResource(R.string.vehicle),
                modifier = Modifier
                    .padding(end = 6.dp)
                    .height(92.dp)
                    .width(92.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(start = 6.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = vehicle.licensePlate,
                        color = Color.Red,
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold
                    )

                    // Declare text, color, and backgroundColor variables here
                    val (text, color, backgroundColor) = if (vehicle.availability) {
                        Triple(
                            stringResource(R.string.available),
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.secondaryContainer
                        )
                    } else {
                        Triple(
                            stringResource(R.string.unavailable),
                            MaterialTheme.colorScheme.error,
                            MaterialTheme.colorScheme.errorContainer
                        )
                    }

                    Box(
                        modifier = Modifier
                            .height(24.dp)
                            .background(
                                color = backgroundColor,
                                shape = RoundedCornerShape(12.dp)
                            )
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            text = text,
                            color = color
                        )
                    }
                }
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(vehicle.brand)
                        }
                        append(" ")
                        append(vehicle.model)
                    },
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = stringResource(R.string.location)
                        )
                        Text(text = location, style = MaterialTheme.typography.bodySmall)
                    }
                    Row {
                        Icon(
                            imageVector = Icons.Default.PriceChange,
                            contentDescription = stringResource(R.string.price)
                        )
                        Text(
                            text = vehicle.price.toString(),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .width(42.dp)
                        )
                    }
                }
            }
        }
    }
}

