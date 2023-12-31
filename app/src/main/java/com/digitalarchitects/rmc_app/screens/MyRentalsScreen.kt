package com.digitalarchitects.rmc_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Toys
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.components.RmcAppBar
import com.digitalarchitects.rmc_app.components.RmcSpacer
import com.digitalarchitects.rmc_app.data.myrentals.MyRentalTab
import com.digitalarchitects.rmc_app.data.myrentals.MyRentalsUIEvent
import com.digitalarchitects.rmc_app.data.myrentals.MyRentalsViewModel
import com.digitalarchitects.rmc_app.room.LocalRental

@Composable
fun MyRentalsScreen(
    viewModel: MyRentalsViewModel,
    navigateToScreen: (String) -> Unit
) {
    val navigateToScreenEvent by viewModel.navigateToScreen.collectAsState()
    if (navigateToScreenEvent != null) {
        navigateToScreen(navigateToScreenEvent!!.name)
    }
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
//      TODO
    }

    Scaffold(
        topBar = {
            RmcAppBar(
                title = R.string.screen_title_my_rentals,
                navigationIcon = Icons.Rounded.ArrowBack,
                navigateUp = {
                    viewModel.onEvent(MyRentalsUIEvent.NavigateUpButtonClicked)
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
                    MyRentalTab.values().forEachIndexed { index, tab ->
                        Tab(
                            text = { Text(stringResource(id = tab.tabNameResourceId)) },
                            selected = uiState.selectedTab == tab,
                            onClick = { uiState.selectedTab = tab },
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                RmcSpacer()

                LazyColumn {
//                  TODO  val filteredList = getFilteredRentals(list, selectedTab)
//                  TODO for each rental in filteredList
//                  TODO MyRentalItem(rental)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRentalItem(rental :LocalRental) {
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clickable {
                isSheetOpen = true
            },
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(text = stringResource(id = R.string.vehicle))
                Text(text = ": ${rental.vehicleId}")
            }
            Row {
                Text(text = stringResource(id = R.string.date))
                Text(text = ": ${rental.date}")
            }
            Row {
                Text(text = stringResource(id = R.string.price))
                Text(text = ": ${rental.price}")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(text = stringResource(id = R.string.km))
                Text(text = ": ${rental.distanceTravelled}")
            }
            Row {
                Text(text = stringResource(id = R.string.points))
                Text(text = ": ${rental.score}")
            }
        }
    }
    Divider(modifier = Modifier.padding(8.dp))
    if (isSheetOpen) {
        ModalBottomSheet(
            modifier = Modifier.height(500.dp),
            sheetState = sheetState,
            onDismissRequest = { isSheetOpen = false },
        ) {
            Column(
                modifier = Modifier
                    .height(500.dp)
                    .padding(16.dp, 0.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "${rental.date}",
                    color = Color.Red,
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )
                Row {
                    Row {
                        Icon(
                            imageVector = Icons.Default.AddLocation,"location"
                        )
                        Text(text = "Stad")
                    }
                    Row {
                        Icon(
                            imageVector = Icons.Default.LocalOffer,"price"
                        )
                        Text(text = "99,5")
                    }
                }
                Row {
                    Row {
                        Icon(
                            imageVector = Icons.Default.Flag,"afstand"
                        )
                        Text(text = "0 km")
                    }
                    Row {
                        Icon(
                            imageVector = Icons.Default.Toys,"score"
                        )
                        Text(text = "+ 3")
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Box(
                        modifier = Modifier
                            .height(24.dp)
                            .background(
                                color = Color.Red, shape = RoundedCornerShape(12.dp)
                            )
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 18.dp),
                            text = stringResource(id = R.string.status),
                            color = Color.White,
                        )

                        Box(
                            modifier = Modifier
                                .height(24.dp)
                                .background(
                                    color = Color.Red, shape = RoundedCornerShape(12.dp)
                                )
                        ) {
                            Text(
                                modifier = Modifier.padding(horizontal = 18.dp),
                                text = stringResource(id = R.string.cancel_rental),
                                color = Color.White,
                            )
                        }
                        Box(
                            modifier = Modifier
                                .height(24.dp)
                                .background(
                                    color = Color.Magenta, shape = RoundedCornerShape(12.dp)
                                )
                        ) {
                            Text(
                                modifier = Modifier.padding(horizontal = 12.dp),
                                text = stringResource(id = R.string.route_to_car),
                                color = Color.White,
                            )
                        }

                    }
                }
                RmcSpacer()
                Column {
//                    VehicleListItem(vehicle = vehicle)
                }
                RmcSpacer()
                RmcSpacer()
            }
        }
    }
}