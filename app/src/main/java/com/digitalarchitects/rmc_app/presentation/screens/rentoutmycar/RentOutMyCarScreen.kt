package com.digitalarchitects.rmc_app.presentation.screens.rentoutmycar

import android.util.Log
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
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
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
import com.digitalarchitects.rmc_app.presentation.components.RmcAppBar
import com.digitalarchitects.rmc_app.presentation.components.RmcRentalDetailsOwner
import com.digitalarchitects.rmc_app.presentation.components.RmcTextBadge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RentOutMyCarScreen(
    viewModel: RentOutMyCarViewModel,
    navigateToScreen: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    val rentalBottomSheet = rememberModalBottomSheetState()

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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                TabRow(
                    selectedTabIndex = uiState.selectedTab.ordinal,
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = colorResource(R.color.white),
                    contentColor = colorResource(R.color.purple_500)
                ) {
                    RentalTab.values().forEach { tab ->
                        Tab(
                            text = { Text(stringResource(id = tab.tabNameResourceId)) },
                            selected = uiState.selectedTab == tab,
                            onClick = { viewModel.onEvent(RentOutMyCarUIEvent.SelectTab(tab)) },
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
                        RentalTab.PENDING -> {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 56.dp)
                                    .padding(24.dp)
                            ) {
                                itemsIndexed(uiState.pendingRentalsList) { index, rental ->
                                    RentalListItem(
                                        rental = rental.first,
                                        vehicle = rental.second,
                                        user = rental.third,
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
                                        Divider(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 16.dp),
                                            color = MaterialTheme.colorScheme.onSurface.copy(
                                                alpha = 0.08f
                                            ),
                                            thickness = 1.dp
                                        )
                                }
                            }
                        }

                        RentalTab.OPEN -> {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 56.dp)
                                    .padding(24.dp)
                            ) {
                                itemsIndexed(uiState.openRentalsList) { index, rental ->
                                    RentalListItem(
                                        rental = rental.first,
                                        vehicle = rental.second,
                                        user = rental.third,
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
                                        Divider(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 16.dp),
                                            color = MaterialTheme.colorScheme.onSurface.copy(
                                                alpha = 0.08f
                                            ),
                                            thickness = 1.dp
                                        )
                                }
                            }
                        }

                        RentalTab.HISTORY -> {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 56.dp)
                                    .padding(24.dp)
                            ) {
                                itemsIndexed(uiState.historyRentalsList) { index, rental ->
                                    RentalListItem(
                                        rental = rental.first,
                                        vehicle = rental.second,
                                        user = rental.third,
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
                                        Divider(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 16.dp),
                                            color = MaterialTheme.colorScheme.onSurface.copy(
                                                alpha = 0.08f
                                            ),
                                            thickness = 1.dp
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
            RentalDetailsBottomSheet(
                details = details,
                sheetState = rentalBottomSheet,
                showAcceptButton = details.first.status == RentalStatus.PENDING,
                showRejectButton = details.first.status == RentalStatus.PENDING || details.first.status == RentalStatus.APPROVED,
                onRejectClick = {
                    viewModel.onEvent(RentOutMyCarUIEvent.RejectRental(details.first.rentalId))
                    viewModel.onEvent(RentOutMyCarUIEvent.CancelShowRentalDetails)
                },
                onAcceptClick = {
                    viewModel.onEvent(RentOutMyCarUIEvent.AcceptRental(details.first.rentalId))
                    viewModel.onEvent(RentOutMyCarUIEvent.CancelShowRentalDetails)
                },
                onCancel = {
                    viewModel.onEvent(RentOutMyCarUIEvent.CancelShowRentalDetails)
                }
            )
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RentalDetailsBottomSheet(
    details: Triple<Rental, Vehicle, User>,
    sheetState: SheetState,
    showAcceptButton: Boolean,
    showRejectButton: Boolean,
    onRejectClick: () -> Unit,
    onAcceptClick: () -> Unit,
    onCancel: () -> Unit
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onCancel,
    ) {
        RmcRentalDetailsOwner(
            rental = details.first,
            vehicle = details.second,
            user = details.third,
            showAcceptButton = showAcceptButton,
            showRejectButton = showRejectButton,
            onRejectClick = { onRejectClick() },
            onAcceptClick = { onAcceptClick() }
        )
    }
}

@Composable
fun RentalListItem(
    rental: Rental,
    vehicle: Vehicle,
    user: User,
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
                Text(text = stringResource(id = R.string.renter))
                Text(text = ": ${user.firstName} ${user.lastName}")
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(text = stringResource(id = R.string.vehicle))
                Text(text = ": ${vehicle.brand} ${vehicle.model}")
            }
        }
    }
}

