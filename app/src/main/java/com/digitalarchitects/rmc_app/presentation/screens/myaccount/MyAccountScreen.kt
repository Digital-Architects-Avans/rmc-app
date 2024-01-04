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
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import com.digitalarchitects.rmc_app.presentation.RmcScreen
import com.digitalarchitects.rmc_app.presentation.components.RmcAppBar
import com.digitalarchitects.rmc_app.presentation.components.RmcFilledButton
import com.digitalarchitects.rmc_app.presentation.components.RmcLogoText
import com.digitalarchitects.rmc_app.presentation.components.RmcOutlinedButton
import com.digitalarchitects.rmc_app.presentation.components.RmcSpacer
import com.digitalarchitects.rmc_app.presentation.components.RmcUserIcon
import com.digitalarchitects.rmc_app.presentation.components.SmallHeadingTextComponent


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

    Scaffold(
        topBar = {
            RmcAppBar(
                title = R.string.screen_title_my_account,
                navigationIcon = Icons.Rounded.Close,
                navigateUp = {
                    navigateToScreen(RmcScreen.RentACar.name)
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
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
                        navigateToScreen(RmcScreen.EditMyAccount.name)
                    }
                )
                uiState.currentUser?.let {
                    SmallHeadingTextComponent(
                        value = it.firstName
                    )
                }
                RmcSpacer()
                RmcFilledButton(
                    value = stringResource(R.string.my_vehicles),
                    icon = Icons.Filled.DirectionsCar,
                    onClick = {
                        navigateToScreen(RmcScreen.MyVehicles.name)
                    }
                )
                RmcFilledButton(
                    value = stringResource(R.string.rent_my_car),
                    icon = Icons.Filled.Key,
                    onClick = {
                        navigateToScreen("RentOutMyCar")
                    }
                )
                RmcFilledButton(
                    value = stringResource(R.string.my_rentals),
                    icon = Icons.Filled.CarRental,
                    onClick = {
                        navigateToScreen(RmcScreen.MyRentals.name)
                    }
                )
                RmcOutlinedButton(
                    value = stringResource(R.string.logout),
                    icon = Icons.Filled.Output,
                    onClick = {
                        viewModel.onEvent(MyAccountUIEvent.OnLogoutButtonClicked)
                        navigateToScreen(RmcScreen.Welcome.name)
                    }
                )
                RmcSpacer()
            }
        }
    }
}
