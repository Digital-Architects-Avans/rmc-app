package com.digitalarchitects.rmc_app.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Output
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.components.LargeHeadingTextComponent
import com.digitalarchitects.rmc_app.components.RmcFilledButton
import com.digitalarchitects.rmc_app.components.RmcFilledTonalButton
import com.digitalarchitects.rmc_app.components.RmcLogoText
import com.digitalarchitects.rmc_app.components.RmcOutlinedButton
import com.digitalarchitects.rmc_app.components.RmcSpacer
import com.digitalarchitects.rmc_app.components.RmcUserIcon
import com.digitalarchitects.rmc_app.components.SmallHeadingTextComponent
import com.digitalarchitects.rmc_app.model.User


@Composable
fun MyAccountScreen(user: User) {
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
        ){

            RmcLogoText()
            RmcUserIcon(userIcon = user.imageResourceId)
            SmallHeadingTextComponent(value = "${user.firstName} ${user.lastName}")
            RmcSpacer()
            RmcFilledButton(value = stringResource(R.string.my_vehicles), icon = Icons.Filled.DirectionsCar) {}
            RmcFilledButton(value = stringResource(R.string.rent_out_my_car), icon = Icons.Filled.Key) {}
            RmcFilledButton(value = stringResource(R.string.my_rentals), icon = Icons.Filled.CarRental) {}
            RmcOutlinedButton(value = stringResource(R.string.logout), icon = Icons.Filled.Output) {}
            RmcSpacer()

        }
    }
}
