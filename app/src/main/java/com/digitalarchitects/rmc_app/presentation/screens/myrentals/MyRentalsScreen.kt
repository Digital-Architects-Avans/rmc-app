package com.digitalarchitects.rmc_app.presentation.screens.myrentals

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.domain.model.Rental
import com.digitalarchitects.rmc_app.domain.model.RentalStatus
import com.digitalarchitects.rmc_app.domain.model.User
import com.digitalarchitects.rmc_app.domain.model.Vehicle
import com.digitalarchitects.rmc_app.presentation.RmcScreen
import com.digitalarchitects.rmc_app.presentation.components.MyRentalDetails
import com.digitalarchitects.rmc_app.presentation.components.RmcAppBar
import com.digitalarchitects.rmc_app.presentation.components.RmcTextBadge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRentalsScreen(
    viewModel: MyRentalsViewModel,
    navigateToScreen: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    val rentalBottomSheet = rememberModalBottomSheetState()

    LaunchedEffect(
        uiState.myRentalsList
    ) {
        viewModel.onEvent(MyRentalsUIEvent.FetchMyRentals)
    }

    Scaffold(
        topBar = {
            RmcAppBar(
                title = R.string.screen_title_my_rentals,
                navigationIcon = Icons.Rounded.Close,
                navigateUp = {
                    navigateToScreen(RmcScreen.RentACar.name)
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
                    .padding(16.dp)
            ) {
                TabRow(
                    selectedTabIndex = uiState.selectedTab.ordinal,
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = colorResource(R.color.white),
                    contentColor = colorResource(R.color.purple_500)
                ) {
                    MyRentalTab.values().forEach { tab ->
                        Tab(
                            text = { Text(stringResource(id = tab.tabNameResourceId)) },
                            selected = uiState.selectedTab == tab,
                            onClick = { viewModel.onEvent(MyRentalsUIEvent.SelectTab(tab)) },
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                if (!uiState.isLoading) {
                    when (uiState.selectedTab) {
                        MyRentalTab.OPEN -> {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 56.dp)
                                    .padding(24.dp)
                            ) {
                                itemsIndexed(uiState.myOpenRentalsList) { index, rental ->
                                    MyRentalListItem(
                                        rental = rental.first,
                                        vehicle = rental.second,
                                        onItemClick = {
                                            viewModel.onEvent(
                                                MyRentalsUIEvent.ShowRentalDetails(
                                                    tab = MyRentalTab.OPEN,
                                                    index = index
                                                )
                                            )
                                        }
                                    )

                                    if (index < uiState.myOpenRentalsList.lastIndex)
                                        HorizontalDivider(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 16.dp),
                                            thickness = 1.dp,
                                            color = MaterialTheme.colorScheme.onSurface.copy(
                                                alpha = 0.08f
                                            )
                                        )
                                }
                            }
                        }

                        MyRentalTab.HISTORY -> {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 56.dp)
                                    .padding(24.dp)
                            ) {
                                itemsIndexed(uiState.myHistoryRentalList) { index, rental ->
                                    MyRentalListItem(
                                        rental = rental.first,
                                        vehicle = rental.second,
                                        onItemClick = {
                                            viewModel.onEvent(
                                                MyRentalsUIEvent.ShowRentalDetails(
                                                    tab = MyRentalTab.HISTORY,
                                                    index = index
                                                )
                                            )
                                        }
                                    )

                                    if (index < uiState.myHistoryRentalList.lastIndex)
                                        HorizontalDivider(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 16.dp),
                                            thickness = 1.dp,
                                            color = MaterialTheme.colorScheme.onSurface.copy(
                                                alpha = 0.08f
                                            )
                                        )
                                }
                            }

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
            }
        }

        // Display vehicle details in a modal bottom sheet when a vehicle is selected
        uiState.selectedRentalItem?.let { details ->

            // Format the address to drop the country
            val addressAsList = details.second.address.split(",")
            val detailedAddress = addressAsList[0] + ", " + addressAsList[1]

            MyRentalDetailsBottomSheet(
                details = details,
                location = detailedAddress,
                sheetState = rentalBottomSheet,
                showButtons = details.first.status == RentalStatus.PENDING || details.first.status == RentalStatus.APPROVED,
                onCancelRentalClick = {
                    viewModel.onEvent(MyRentalsUIEvent.CancelRental(details.first.rentalId))
                    viewModel.onEvent(MyRentalsUIEvent.CancelShowRentalDetails)
                },
                onRouteClick = {
                    viewModel.onEvent(MyRentalsUIEvent.RouteToRental(details.first.rentalId))
                    viewModel.onEvent(MyRentalsUIEvent.CancelShowRentalDetails)
                },
                onCancel = {
                    viewModel.onEvent(MyRentalsUIEvent.CancelShowRentalDetails)
                }
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRentalDetailsBottomSheet(
    details: Triple<Rental, Vehicle, User>,
    location: String,
    sheetState: SheetState,
    showButtons: Boolean,
    onCancelRentalClick: () -> Unit,
    onRouteClick: () -> Unit,
    onCancel: () -> Unit
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onCancel,
    ) {
        MyRentalDetails(
            rental = details.first,
            vehicle = details.second,
            location = location,
            user = details.third,
            showButtons = showButtons,
            onCancelRentalClick = { onCancelRentalClick() },
            onRouteClick = { onRouteClick() }
        )
    }
}

@Composable
fun MyRentalListItem(
    rental: Rental,
    vehicle: Vehicle,
    onItemClick: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clickable { onItemClick() },
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(text = stringResource(id = R.string.date))
                Text(text = ": ${rental.date}")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(text = stringResource(id = R.string.vehicle))
                Text(text = ": ${vehicle.brand} ${vehicle.model}")
            }
            val (rentalStatus, labelTextColor, backgroundColor) = when (rental.status) {
                RentalStatus.PENDING ->
                    Triple(
                        stringResource(R.string.pending),
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.secondaryContainer
                    )

                RentalStatus.APPROVED ->
                    Triple(
                        stringResource(R.string.approved),
                        colorResource(id = R.color.primary_green_text),
                        colorResource(id = R.color.primary_green_bg)
                    )

                RentalStatus.DENIED ->
                    Triple(
                        stringResource(R.string.denied),
                        MaterialTheme.colorScheme.error,
                        MaterialTheme.colorScheme.errorContainer
                    )

                RentalStatus.CANCELLED ->
                    Triple(
                        stringResource(R.string.cancelled),
                        MaterialTheme.colorScheme.error,
                        MaterialTheme.colorScheme.errorContainer
                    )
            }
            RmcTextBadge(
                label = rentalStatus,
                labelTextColor = labelTextColor,
                labelBackgroundColor = backgroundColor
            )
        }
    }
}