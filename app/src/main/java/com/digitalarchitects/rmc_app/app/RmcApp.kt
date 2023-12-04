package com.digitalarchitects.rmc_app.app

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.components.LargeHeadingTextComponent
import com.digitalarchitects.rmc_app.data.login.LoginViewModel
import com.digitalarchitects.rmc_app.screens.LoginScreen
import com.digitalarchitects.rmc_app.screens.RegisterScreen
import com.digitalarchitects.rmc_app.screens.SearchScreen
import com.digitalarchitects.rmc_app.screens.TermsAndConditionsScreen
import com.digitalarchitects.rmc_app.screens.WelcomeScreen
import com.digitalarchitects.rmc_app.ui.theme.RmcAppTheme

enum class RmcScreen(@StringRes val title: Int) {
    Welcome(title = R.string.screen_title_welcome),
    Register(title = R.string.screen_title_register),
    TermsAndConditions(title = R.string.screen_title_terms),
    Login(title = R.string.screen_title_login),
    RentACar(title = R.string.screen_title_rent_a_car),
    Search(title = R.string.screen_title_search),
    VehicleDetails(title = R.string.screen_title_vehicle_details),
    MyRentals(title = R.string.screen_title_my_rentals),
    RentMyCar(title = R.string.screen_title_rent_my_car),
    MyVehicles(title = R.string.screen_title_my_vehicles),
    RegisterVehicle(title = R.string.screen_title_register_vehicle),
    MyAccount(title = R.string.screen_title_my_account),
    EditAccount(title = R.string.screen_title_edit_account)
}

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RmcAppBar(
    @StringRes currentScreenTitle: Int,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { LargeHeadingTextComponent(stringResource(currentScreenTitle)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun RmcApp(
    viewModel: LoginViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = RmcScreen.valueOf(
        backStackEntry?.destination?.route ?: RmcScreen.Login.name
    )

    Scaffold(
        topBar = {
            RmcAppBar(
                currentScreenTitle = currentScreen.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = RmcScreen.Welcome.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = RmcScreen.Welcome.name) {
                WelcomeScreen(
                    onRegisterButtonClicked = { navController.navigate(RmcScreen.Register.name) },
                    onLoginButtonClicked = { navController.navigate(RmcScreen.Login.name) }
                )
            }
            composable(route = RmcScreen.Register.name) {
                RegisterScreen(
                    onTermsAndConditionsTextClicked = { navController.navigate(RmcScreen.TermsAndConditions.name) },
                    onLoginTextClicked = { navController.navigate(RmcScreen.Login.name) },
                    onRegisterButtonClicked = { navController.navigate(RmcScreen.RentACar.name) }
                )
            }
            composable(route = RmcScreen.TermsAndConditions.name) {
                TermsAndConditionsScreen(navController = navController)
            }
            composable(route = RmcScreen.Login.name) {
                LoginScreen(navController = navController)
            }
            composable(route = RmcScreen.RentACar.name) {
                TODO("Implement Rent A Car screen")
                // RentACarScreen()
            }
            composable(route = RmcScreen.Search.name) {
                SearchScreen()
            }
            composable(route = RmcScreen.VehicleDetails.name) {
                TODO("Implement VehicleDetails screen")
                // VehicleDetailsScreen()
            }
            composable(route = RmcScreen.MyRentals.name) {
                TODO("Implement MyRentals screen")
                // MyRentalsScreen()
            }
            composable(route = RmcScreen.RentMyCar.name) {
                TODO("Implement RentMyCar screen")
                // RentMyCarScreen()
            }
            composable(route = RmcScreen.MyVehicles.name) {
                TODO("Implement MyVehicles screen")
                // MyVehiclesScreen()
            }
            composable(route = RmcScreen.RegisterVehicle.name) {
                TODO("Implement RegisterVehicle screen")
                // RegisterVehicleScreen()
            }
            composable(route = RmcScreen.MyAccount.name) {
                TODO("Implement MyAccount screen")
                // MyAccountScreen()
            }
            composable(route = RmcScreen.EditAccount.name) {
                TODO("Implement EditAccount screen")
                // EditAccountScreen()
            }
        }
    }
}


@Preview
@Composable
fun Preview() {
    RmcAppTheme {
        RmcApp()
    }
}