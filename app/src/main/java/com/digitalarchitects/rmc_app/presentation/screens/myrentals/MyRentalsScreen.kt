package com.digitalarchitects.rmc_app.presentation.screens.myrentals

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.domain.model.RentalStatus
import com.digitalarchitects.rmc_app.presentation.RmcScreen
import com.digitalarchitects.rmc_app.presentation.components.RmcAppBar
import com.digitalarchitects.rmc_app.presentation.components.RmcDivider
import com.digitalarchitects.rmc_app.presentation.components.RmcRentalDetails
import com.digitalarchitects.rmc_app.presentation.components.RmcRentalListItem
import com.digitalarchitects.rmc_app.presentation.components.RmcSpacer
import com.digitalarchitects.rmc_app.presentation.components.RmcVehicleListItem
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRentalsScreen(
    viewModel: MyRentalsViewModel,
    navigateToScreen: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    val rentalBottomSheet = rememberModalBottomSheetState()

    val scope = rememberCoroutineScope()

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

                    // Open Google Maps when the user clicks on the route button
                    if (uiState.routeToRental != null) {

                        // Set and reset location
                        val vehicleLocation: String = uiState.routeToRental!!
                        viewModel.onEvent(MyRentalsUIEvent.RouteToRental(null))

                        // Open Google Maps
                        val context: Context = LocalContext.current
                        val gmmIntentUri = Uri.parse("geo:0,0?q=${vehicleLocation}")
                        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                        mapIntent.setPackage("com.google.android.apps.maps")
                        startActivity(context, mapIntent, null)
                    }

                    // Start My Rentals screen content
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


        // Display rental and vehicle details in a modal bottom sheet when a vehicle is selected
        uiState.selectedRentalItem?.let { details ->
            ModalBottomSheet(
                sheetState = rentalBottomSheet,
                onDismissRequest = { viewModel.onEvent(MyRentalsUIEvent.CancelShowRentalDetails) },
            ) {
                val today = Clock.System.now().toLocalDateTime(
                    TimeZone.currentSystemDefault()
                ).date
                RmcRentalDetails(
                    rental = details.first,
                    vehicle = details.second,
                    user = details.third,
                    showRejectButton = details.first.status == RentalStatus.PENDING || details.first.date >= today && details.first.status == RentalStatus.APPROVED,
                    onCancelRentalClick = {
                        viewModel.onEvent(MyRentalsUIEvent.CancelRental(details.first.rentalId))
                        viewModel.onEvent(MyRentalsUIEvent.CancelShowRentalDetails)
                    },
                    onRouteClick = {
                        viewModel.onEvent(MyRentalsUIEvent.CancelShowRentalDetails)
                        viewModel.onEvent(
                            MyRentalsUIEvent.RouteToRental(details.second.address)
                        )
                    },
                )
                RmcDivider()
                RmcVehicleListItem(details.second) {}
                RmcSpacer(32)
            }
        }
    }
}