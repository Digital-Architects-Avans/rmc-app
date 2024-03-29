package com.digitalarchitects.rmc_app.presentation.screens.rentoutmycar

import android.util.Log
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.domain.model.RentalStatus
import com.digitalarchitects.rmc_app.presentation.RmcScreen
import com.digitalarchitects.rmc_app.presentation.components.RmcAppBar
import com.digitalarchitects.rmc_app.presentation.components.RmcDivider
import com.digitalarchitects.rmc_app.presentation.components.RmcRentalDetails
import com.digitalarchitects.rmc_app.presentation.components.RmcRentalListItem
import com.digitalarchitects.rmc_app.presentation.components.RmcSpacer
import com.digitalarchitects.rmc_app.presentation.components.RmcVehicleListItem
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RentOutMyCarScreen(
    viewModel: RentOutMyCarViewModel,
    navigateToScreen: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    val rentalBottomSheet = rememberModalBottomSheetState()

    val scope = rememberCoroutineScope()

    LaunchedEffect(
        uiState.listOfRentalsForUser
    ) {
        viewModel.onEvent(RentOutMyCarUIEvent.FetchRentals)
    }

    Log.d("RentOutMyCarScreen", "RentOutMyCarScreen: ${uiState.listOfRentalsForUser}")

    Scaffold(
        topBar = {
            RmcAppBar(
                title = R.string.screen_title_rent_my_car,
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
                    Tab(
                        text = { Text(stringResource(id = R.string.status_pending, uiState.pendingRentalsList.count())) },
                        selected = uiState.selectedTab == RentalTab.PENDING,
                        onClick = { viewModel.onEvent(RentOutMyCarUIEvent.SelectTab(RentalTab.PENDING)) },
                    )
                    Tab(
                        text = { Text(stringResource(id = R.string.status_open, uiState.openRentalsList.count())) },
                        selected = uiState.selectedTab == RentalTab.OPEN,
                        onClick = { viewModel.onEvent(RentOutMyCarUIEvent.SelectTab(RentalTab.OPEN)) },
                    )
                    Tab(
                        text = { Text(stringResource(id = R.string.status_history, uiState.historyRentalsList.count())) },
                        selected = uiState.selectedTab == RentalTab.HISTORY,
                        onClick = { viewModel.onEvent(RentOutMyCarUIEvent.SelectTab(RentalTab.HISTORY)) },
                    )
                }
                RmcSpacer(16)
                Column {
                    if (!uiState.isLoading) {
                        when (uiState.selectedTab) {
                            RentalTab.PENDING -> {
                                LazyColumn {
                                    itemsIndexed(uiState.pendingRentalsList) { index, rental ->
                                        RmcRentalListItem(
                                            rental = rental.first,
                                            vehicle = rental.second,
                                            user = rental.third,
                                            ownerView = true,
                                            onItemClick = {
                                                viewModel.onEvent(
                                                    RentOutMyCarUIEvent.ShowRentalDetails(
                                                        tab = RentalTab.PENDING,
                                                        index = index
                                                    )
                                                )
                                            }
                                        )
                                        if (index < uiState.pendingRentalsList.lastIndex)
                                            RmcDivider()
                                    }
                                }
                                if (uiState.pendingRentalsList.isEmpty()) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = stringResource(R.string.no_rentals_found),
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                            }

                            RentalTab.OPEN -> {
                                LazyColumn {
                                    itemsIndexed(uiState.openRentalsList) { index, rental ->
                                        RmcRentalListItem(
                                            rental = rental.first,
                                            vehicle = rental.second,
                                            user = rental.third,
                                            ownerView = true,
                                            onItemClick = {
                                                viewModel.onEvent(
                                                    RentOutMyCarUIEvent.ShowRentalDetails(
                                                        tab = RentalTab.OPEN,
                                                        index = index
                                                    )
                                                )
                                            }
                                        )
                                        if (index < uiState.openRentalsList.lastIndex)
                                            RmcDivider()
                                    }
                                }
                                if (uiState.openRentalsList.isEmpty()) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = stringResource(R.string.no_rentals_found),
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                            }

                            RentalTab.HISTORY -> {
                                LazyColumn {
                                    itemsIndexed(uiState.historyRentalsList) { index, rental ->
                                        RmcRentalListItem(
                                            rental = rental.first,
                                            vehicle = rental.second,
                                            user = rental.third,
                                            ownerView = true,
                                            onItemClick = {
                                                viewModel.onEvent(
                                                    RentOutMyCarUIEvent.ShowRentalDetails(
                                                        tab = RentalTab.HISTORY,
                                                        index = index
                                                    )
                                                )
                                            }
                                        )
                                        if (index < uiState.historyRentalsList.lastIndex)
                                            RmcDivider()
                                    }
                                }
                                if (uiState.historyRentalsList.isEmpty()) {
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

        // Display rental and vehicle details in a modal bottom sheet when a vehicle is selected
        uiState.selectedRentalItem?.let { details ->
            ModalBottomSheet(
                sheetState = rentalBottomSheet,
                onDismissRequest = { viewModel.onEvent(RentOutMyCarUIEvent.CancelShowRentalDetails) },
            ) {
                val today = Clock.System.now().toLocalDateTime(
                    TimeZone.currentSystemDefault()
                ).date
                RmcRentalDetails(
                    rental = details.first,
                    vehicle = details.second,
                    user = details.third,
                    ownerView = true,
                    showAcceptButton = details.first.status == RentalStatus.PENDING,
                    showRejectButton = details.first.status == RentalStatus.PENDING || details.first.date >= today && details.first.status == RentalStatus.APPROVED,
                    onRejectClick = {
                        viewModel.onEvent(RentOutMyCarUIEvent.RejectRental(details.first.rentalId))
                        viewModel.onEvent(RentOutMyCarUIEvent.CancelShowRentalDetails)
                    },
                    onAcceptClick = {
                        viewModel.onEvent(RentOutMyCarUIEvent.AcceptRental(details.first.rentalId))
                        viewModel.onEvent(RentOutMyCarUIEvent.CancelShowRentalDetails)
                    }
                )
                RmcDivider()
                RmcVehicleListItem(details.second) {
                    scope.launch {
                        rentalBottomSheet.hide()
                    }
                    navigateToScreen(RmcScreen.MyVehicles.name)
                }
                RmcSpacer(32)
            }
        }
    }
}