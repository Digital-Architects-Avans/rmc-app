package com.digitalarchitects.rmc_app.presentation.screens.myaccount

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
import com.digitalarchitects.rmc_app.presentation.components.RmcFilledButton
import com.digitalarchitects.rmc_app.presentation.components.RmcLogoText
import com.digitalarchitects.rmc_app.presentation.components.RmcOutlinedButton
import com.digitalarchitects.rmc_app.presentation.components.RmcSpacer
import com.digitalarchitects.rmc_app.presentation.components.RmcUserIcon
import com.digitalarchitects.rmc_app.presentation.components.SmallHeadingTextComponent
import com.digitalarchitects.rmc_app.presentation.screens.myaccount.MyAccountUIEvent
import com.digitalarchitects.rmc_app.presentation.screens.myaccount.MyAccountViewModel


@Composable
fun MyAccountScreen(
    viewModel: MyAccountViewModel,
    navigateToScreen: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.onEvent(MyAccountUIEvent.InsertUser)
        viewModel.onEvent(MyAccountUIEvent.ShowUser)
    }
    val navigateToScreenEvent by viewModel.navigateToScreen.collectAsState()
    if (navigateToScreenEvent != null) {
        navigateToScreen(navigateToScreenEvent!!.name)
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
                    viewModel.onEvent(MyAccountUIEvent.onEditMyAccountButtonClicked)
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

                    viewModel.onEvent(MyAccountUIEvent.onMyVehiclesButtonClicked)
                }
            )
            RmcFilledButton(
                value = stringResource(R.string.rent_out_my_car),
                icon = Icons.Filled.Key,
                onClick = {
                    viewModel.onEvent(MyAccountUIEvent.onRentOutMyCarButtonClicked)
                }
            )
            RmcFilledButton(
                value = stringResource(R.string.my_rentals),
                icon = Icons.Filled.CarRental,
                onClick = {
                    viewModel.onEvent(MyAccountUIEvent.onMyRentalsButtonClicked)
                }
            )
            RmcOutlinedButton(
                value = stringResource(R.string.logout),
                icon = Icons.Filled.Output,
                onClick = {
                    viewModel.onEvent(MyAccountUIEvent.onLogoutButtonClicked)
                }
            )
            RmcSpacer()
        }
    }
}
