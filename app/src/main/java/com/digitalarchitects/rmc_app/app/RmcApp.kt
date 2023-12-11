package com.digitalarchitects.rmc_app.app

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.components.RmcAppBar
import com.digitalarchitects.rmc_app.dummyDTO.DummyRentalDTO
import com.digitalarchitects.rmc_app.dummyDTO.DummyUserDTO
import com.digitalarchitects.rmc_app.dummyDTO.DummyVehicleDTO
import com.digitalarchitects.rmc_app.screens.LoginScreen
import com.digitalarchitects.rmc_app.screens.MyAccountScreen
import com.digitalarchitects.rmc_app.screens.MyVehiclesScreen
import com.digitalarchitects.rmc_app.screens.RegisterScreen
import com.digitalarchitects.rmc_app.screens.RentACarScreen
import com.digitalarchitects.rmc_app.screens.RentOutMyCarScreen
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

@Preview(showBackground = true)
@Composable
fun RmcApp(
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
                navigateUp = { navController.navigateUp() },
            )
        }
    ) { innerPadding ->
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
                LoginScreen(
                    // onForgotPasswordTextClicked = { navController.navigate(RmcScreen.ForgotPassword.name) },
                    onLoginButtonClicked = { navController.navigate(RmcScreen.RentACar.name) },
                    onRegisterTextClicked = { navController.navigate(RmcScreen.Register.name) }
                )
            }
            composable(route = RmcScreen.RentACar.name) {
                RentACarScreen(
                    onSearchButtonClicked = { navController.navigate(RmcScreen.Search.name) },
                    onRentMyCarButtonClicked = { navController.navigate(RmcScreen.RentMyCar.name) },
                    onMyRentalsButtonClicked = { navController.navigate(RmcScreen.MyRentals.name) },
                    onMyAccountButtonClicked = { navController.navigate(RmcScreen.MyAccount.name) }
                )
            }
            composable(route = RmcScreen.Search.name) {
                SearchScreen()
            }
            composable(route = RmcScreen.MyRentals.name) {
                TODO("Implement MyRentals screen")
                // MyRentalsScreen()
            }
            composable(route = RmcScreen.RentMyCar.name) {
                val listOfRentals = DummyRentalDTO()
                val listOfVehicles = DummyVehicleDTO()
                val user = DummyUserDTO()
                RentOutMyCarScreen(list = listOfRentals, vehicles = listOfVehicles, user = user)
            }
            composable(route = RmcScreen.MyVehicles.name) {
                val listOfVehicles = DummyVehicleDTO()
                MyVehiclesScreen(list = listOfVehicles)
            }
            composable(route = RmcScreen.RegisterVehicle.name) {
                TODO("Implement RegisterVehicle screen")
                // RegisterVehicleScreen()
            }
            composable(route = RmcScreen.MyAccount.name) {
                val user = DummyUserDTO()
                MyAccountScreen(user = user)
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