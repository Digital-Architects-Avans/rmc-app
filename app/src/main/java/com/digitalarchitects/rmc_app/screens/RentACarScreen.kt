package com.digitalarchitects.rmc_app.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.digitalarchitects.rmc_app.components.RmcFilledButton
import com.digitalarchitects.rmc_app.components.RmcLogoText
import com.digitalarchitects.rmc_app.components.RmcSpacer

@Composable
fun RentACarScreen(
    onSearchButtonClicked: () -> Unit,
    onRentMyCarButtonClicked: () -> Unit,
    onMyRentalsButtonClicked: () -> Unit,
    onMyAccountButtonClicked: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            RmcLogoText()

            RmcSpacer()

            Text(
                text = "Temp menu:",
                style = MaterialTheme.typography.titleLarge
            )
            RmcSpacer()

            RmcFilledButton(
                value = "Search",
                icon = Icons.Filled.Search,
                onClick = onSearchButtonClicked
            )

            RmcSpacer()

            RmcFilledButton(
                value = "Rent My Car",
                icon = Icons.Filled.Key,
                onClick = onRentMyCarButtonClicked
            )

            RmcSpacer()

            RmcFilledButton(
                value = "My rentals",
                icon = Icons.Filled.CarRental,
                onClick = onMyRentalsButtonClicked
            )

            RmcSpacer()

            RmcFilledButton(
                value = "My account",
                icon = Icons.Filled.Person,
                onClick = onMyAccountButtonClicked
            )
        }
    }
}