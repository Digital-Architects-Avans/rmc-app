package com.digitalarchitects.rmc_app.presentation.screens.myaccount

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Output
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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

@Composable
fun MyAccountScreen(
    viewModel: MyAccountViewModel,
    navigateToScreen: (String) -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.currentUser) {
        viewModel.onEvent(MyAccountUIEvent.FetchUser)
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
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RmcSpacer()
                RmcLogoText()
                RmcSpacer(16)
                RmcUserIcon(
                    imageSrc = uiState.profileImgSrc,
                    editEnabled = true,
                    size = dimensionResource(R.dimen.image_size_large),
                    onClick = {
                        navigateToScreen(RmcScreen.EditMyAccount.name)
                    }
                )
                RmcSpacer(16)
                uiState.currentUser?.let {
                    Text(
                        text = "${it.firstName} ${it.lastName}",
                        style = MaterialTheme.typography.titleLarge,
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
                RmcSpacer(8)
                RmcFilledButton(
                    value = stringResource(R.string.rent_my_car),
                    icon = Icons.Filled.Key,
                    onClick = {
                        navigateToScreen(RmcScreen.RentOutMyCar.name)
                    }
                )
                RmcSpacer(8)
                RmcFilledButton(
                    value = stringResource(R.string.my_rentals),
                    icon = Icons.Filled.CarRental,
                    onClick = {
                        navigateToScreen(RmcScreen.MyRentals.name)
                    }
                )
                RmcSpacer(8)
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