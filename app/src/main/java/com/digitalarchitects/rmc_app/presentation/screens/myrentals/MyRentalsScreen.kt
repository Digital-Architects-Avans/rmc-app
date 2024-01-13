package com.digitalarchitects.rmc_app.presentation.screens.myrentals

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.domain.model.Rental
import com.digitalarchitects.rmc_app.domain.model.RentalStatus
import com.digitalarchitects.rmc_app.domain.model.User
import com.digitalarchitects.rmc_app.domain.model.Vehicle
import com.digitalarchitects.rmc_app.presentation.RmcScreen
import com.digitalarchitects.rmc_app.presentation.components.MyRentalDetails
import com.digitalarchitects.rmc_app.presentation.components.RmcAppBar
import com.digitalarchitects.rmc_app.presentation.components.RmcDivider
import com.digitalarchitects.rmc_app.presentation.components.RmcRentalListItem
import com.digitalarchitects.rmc_app.presentation.components.RmcSpacer

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
            Column {
                TabRow(
                    selectedTabIndex = uiState.selectedTab.ordinal,
                    contentColor = MaterialTheme.colorScheme.primary,
                ) {
                    MyRentalTab.values().forEach { tab ->
                        Tab(
                            text = { Text(stringResource(id = tab.tabNameResourceId)) },
                            selected = uiState.selectedTab == tab,
                            onClick = { viewModel.onEvent(MyRentalsUIEvent.SelectTab(tab)) },
                        )
                    }
                }
                RmcSpacer(16)
                Column {
                    if (!uiState.isLoading) {
                        when (uiState.selectedTab) {
                            MyRentalTab.OPEN -> {
                                LazyColumn {
                                    itemsIndexed(uiState.myOpenRentalsList) { index, rental ->
                                        RmcRentalListItem(
                                            rental = rental.first,
                                            vehicle = rental.second,
                                            user = rental.third,
                                            ownerView = false,
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
                                            RmcDivider()
                                    }
                                }
                                if (uiState.myOpenRentalsList.isEmpty()) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = stringResource(R.string.no_rentals_found),
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                            }

                            MyRentalTab.HISTORY -> {
                                LazyColumn {
                                    itemsIndexed(uiState.myHistoryRentalList) { index, rental ->
                                        RmcRentalListItem(
                                            rental = rental.first,
                                            vehicle = rental.second,
                                            user = rental.third,
                                            ownerView = false,
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
                                            RmcDivider()
                                    }
                                }
                                if (uiState.myHistoryRentalList.isEmpty()) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = stringResource(R.string.no_rentals_found),
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                            }

                        }
                    } else {
                        // Show loading indicator or any other UI when isLoading is true
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(50.dp)
                        )
                        }
                    }
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