package com.digitalarchitects.rmc_app.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Output
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.components.RmcFilledButton
import com.digitalarchitects.rmc_app.components.RmcLogoText
import com.digitalarchitects.rmc_app.components.RmcOutlinedButton
import com.digitalarchitects.rmc_app.components.RmcSpacer
import com.digitalarchitects.rmc_app.components.RmcUserIcon
import com.digitalarchitects.rmc_app.components.SmallHeadingTextComponent
import com.digitalarchitects.rmc_app.data.myaccount.MyAccountUIEvent
import com.digitalarchitects.rmc_app.data.myaccount.MyAccountViewModel


@Composable
fun MyAccountScreen(
    viewModel: MyAccountViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.onEvent(MyAccountUIEvent.UpsertUser)
        viewModel.onEvent(MyAccountUIEvent.ShowUser)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.surface,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            RmcLogoText()
            RmcUserIcon(
                userIcon = uiState.imageResourceId,
                size = dimensionResource(R.dimen.image_size_large),
                onClick = {
//                    onEvent(MyAccountUIEvent.onEditMyAccountButtonClicked)
                }
            )
            SmallHeadingTextComponent(
                value = uiState.firstName
//                {{ onEvent(MyAccountUIEvent.ShowUser(viewModel.currentUser)), state.firstname} }
            )
            RmcSpacer()
            RmcFilledButton(
                value = stringResource(R.string.my_vehicles),
                icon = Icons.Filled.DirectionsCar,
                onClick = {
//                    onEvent(MyAccountUIEvent.onMyVehiclesButtonClicked)
                }
            )
            RmcFilledButton(
                value = stringResource(R.string.rent_out_my_car),
                icon = Icons.Filled.Key,
                onClick = {
//                    onEvent(MyAccountUIEvent.onRentOutMyCarButtonClicked)
                }
            )
            RmcFilledButton(
                value = stringResource(R.string.my_rentals),
                icon = Icons.Filled.CarRental,
                onClick = {
//                    onEvent(MyAccountUIEvent.onMyRentalsButtonClicked)
                }
            )
            RmcOutlinedButton(
                value = stringResource(R.string.logout),
                icon = Icons.Filled.Output,
                onClick = {
//                    onEvent(MyAccountUIEvent.onLogoutButtonClicked)
                }
            )
            RmcSpacer()
        }
    }
}