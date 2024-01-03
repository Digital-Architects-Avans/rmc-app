package com.digitalarchitects.rmc_app.presentation.screens.repositorytest

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.presentation.components.ButtonComponent
import com.digitalarchitects.rmc_app.presentation.components.NormalTextComponent

@Composable
fun RepositoryTestScreen(
    viewModel: RepositoryTestViewModel,
    modifier: Modifier = Modifier
) {
    var showResult by remember { mutableStateOf(false) }
    var userButtonText by remember { mutableStateOf("Retrieve Users") }
    var vehicleButtonText by remember { mutableStateOf("Retrieve Vehicles") }
    var rentalButtonText by remember { mutableStateOf("Retrieve Rentals") }

    // Column containing the button and result screen
    Box(
        modifier = modifier.fillMaxSize()
    ) {

        // Display users only when the button is pressed
        if (showResult) {
            when (val userUiState = viewModel.resultUiState) {
                is ResultUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
                is ResultUiState.Success -> ResultScreen(
                    userUiState.msg,
                    userUiState.result,
                    modifier = modifier.fillMaxWidth()
                )

                is ResultUiState.Error -> ErrorScreen(
                    userUiState.error,
                    modifier = modifier.fillMaxSize()
                )
            }
        }

        // Buttons at the bottom
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 4.dp)
                .align(Alignment.BottomStart)
        ) {

            // Buttons to trigger the retrieval of data
            ButtonComponent(
                value = userButtonText,
                onButtonClicked = {
                    userButtonText = "Update Users"
                    vehicleButtonText = "Retrieve Vehicles"
                    rentalButtonText = "Retrieve Rentals"
                    viewModel.getUsers()
                    showResult = true
                },
                isEnabled = true
            )

            ButtonComponent(
                value = vehicleButtonText,
                onButtonClicked = {
                    userButtonText = "Retrieve Users"
                    vehicleButtonText = "Update Vehicles"
                    rentalButtonText = "Retrieve Rentals"
                    viewModel.getVehicles()
                    showResult = true
                },
                isEnabled = true
            )

            ButtonComponent(
                value = rentalButtonText,
                onButtonClicked = {
                    userButtonText = "Retrieve Users"
                    vehicleButtonText = "Retrieve Vehicles"
                    rentalButtonText = "Update Rentals"
                    viewModel.getRentals()
                    showResult = true
                },
                isEnabled = true
            )
        }
    }
}

/**
 * The home screen displaying the loading message.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

/**
 * The home screen displaying error message with re-attempt button.
 */
@Composable
fun ErrorScreen(error: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = error, modifier = Modifier.padding(16.dp))
    }
}

/**
 * ResultScreen displaying users and number of users retrieved.
 */
@Composable
fun ResultScreen(msg: String, users: List<Any?>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            NormalTextComponent(value = msg)
            Spacer(modifier = Modifier.height(20.dp))
        }

        items(users) { user ->
            Text(text = user.toString())
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}