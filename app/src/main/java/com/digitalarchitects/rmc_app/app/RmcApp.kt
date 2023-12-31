package com.digitalarchitects.rmc_app.app

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.data.editmyaccount.EditMyAccountViewModel
import com.digitalarchitects.rmc_app.data.login.LoginViewModel
import com.digitalarchitects.rmc_app.data.myaccount.MyAccountViewModel
import com.digitalarchitects.rmc_app.data.myvehicles.MyVehiclesViewModel
import com.digitalarchitects.rmc_app.data.register.RegisterViewModel
import com.digitalarchitects.rmc_app.data.rentacar.RentACarViewModel
import com.digitalarchitects.rmc_app.data.rentoutmycar.RentOutMyCarViewModel
import com.digitalarchitects.rmc_app.data.repositorytest.RepositoryTestViewModel
import com.digitalarchitects.rmc_app.data.search.SearchViewModel
import com.digitalarchitects.rmc_app.data.termsandconditions.TermsAndConditionsViewModel
import com.digitalarchitects.rmc_app.data.welcome.WelcomeViewModel
import com.digitalarchitects.rmc_app.screens.EditMyAccountScreen
import com.digitalarchitects.rmc_app.screens.LoginScreen
import com.digitalarchitects.rmc_app.screens.MyAccountScreen
import com.digitalarchitects.rmc_app.screens.MyVehiclesScreen
import com.digitalarchitects.rmc_app.screens.RegisterScreen
import com.digitalarchitects.rmc_app.screens.RentACarScreen
import com.digitalarchitects.rmc_app.screens.RentOutMyCarScreen
import com.digitalarchitects.rmc_app.screens.RepositoryTestScreen
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
    MyRentals(title = R.string.screen_title_my_rentals),
    RentOutMyCar(title = R.string.screen_title_rent_my_car),
    MyVehicles(title = R.string.screen_title_my_vehicles),
    RegisterVehicle(title = R.string.screen_title_register_vehicle),
    MyAccount(title = R.string.screen_title_my_account),
    EditMyAccount(title = R.string.screen_title_edit_account),
    RmcTestScreen(title = R.string.rmcTestScreenTitle)
}

@Preview(showBackground = true)
@Composable
fun RmcApp(
    navController: NavHostController = rememberNavController()
) {
    val repositoryTestViewModel: RepositoryTestViewModel = hiltViewModel()
    val welcomeViewModel: WelcomeViewModel = hiltViewModel()
    val registerViewModel: RegisterViewModel = hiltViewModel()
    val termsAndConditionsViewModel: TermsAndConditionsViewModel = hiltViewModel()
    val loginViewModel: LoginViewModel = hiltViewModel()
    val rentACarViewModel: RentACarViewModel = hiltViewModel()
    val searchViewModel: SearchViewModel = hiltViewModel()
    val rentOutMyCarViewModel: RentOutMyCarViewModel = hiltViewModel()
    val myVehiclesViewModel: MyVehiclesViewModel = hiltViewModel()
    val editMyAccountViewModel: EditMyAccountViewModel = hiltViewModel()

    val myAccountViewModel: MyAccountViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = RmcScreen.Welcome.name,
    ) {
        composable(route = RmcScreen.Welcome.name) {
            WelcomeScreen(
                viewModel = welcomeViewModel,
                navigateToScreen = { route -> navController.navigate(route) }
            )
        }
        composable(route = RmcScreen.Register.name) {
            RegisterScreen(
                viewModel = registerViewModel,
                navigateToScreen = { route -> navController.navigate(route) }
            )
        }
        composable(route = RmcScreen.TermsAndConditions.name) {
            TermsAndConditionsScreen(
                viewModel = termsAndConditionsViewModel,
                navigateToScreen = { route -> navController.navigate(route) }
            )
        }
        composable(route = RmcScreen.Login.name) {
            LoginScreen(
                // onForgotPasswordTextClicked = { navController.navigate(RmcScreen.ForgotPassword.name) },
                viewModel = loginViewModel,
                navigateToScreen = { route -> navController.navigate(route) }
            )
        }
        composable(route = RmcScreen.RentACar.name) {
            RentACarScreen(
                viewModel = rentACarViewModel,
                navigateToScreen = { route -> navController.navigate(route) }
            )
        }
        composable(route = RmcScreen.Search.name) {
            SearchScreen(
                viewModel = searchViewModel,
                navigateToScreen = { route -> navController.navigate(route) }
            )
        }
        composable(route = RmcScreen.MyRentals.name) {
//             TODO CREATE SCREEN
//             MyRentalsScreen(
//                  viewModel = viewModel as MyRentalsViewModel,
//                  navigateToScreen = { route -> navController.navigate(route) }
//             )
        }
        composable(route = RmcScreen.RentOutMyCar.name) {
//            val listOfRentals = DummyRentalDTO()
//            val listOfVehicles = DummyVehicleDTO()
//            val user = DummyUserDTO()
            RentOutMyCarScreen(
//                list = listOfRentals, vehicles = listOfVehicles, user = user
                viewModel = rentOutMyCarViewModel,
                navigateToScreen = { route -> navController.navigate(route) }
            )
        }
        composable(route = RmcScreen.MyVehicles.name) {
//            val listOfVehicles = DummyVehicleDTO()
            MyVehiclesScreen(
//                list = listOfVehicles
                viewModel = myVehiclesViewModel,
                navigateToScreen = { route -> navController.navigate(route) }
            )
        }
        composable(route = RmcScreen.RegisterVehicle.name) {
//            TODO("Implement RegisterVehicle screen")
//             RegisterVehicleScreen(
//                 viewModel = viewModel as RegisterVehicleViewModel,
//                 navigateToScreen = { route -> navController.navigate(route) }
//             )
        }
        composable(route = RmcScreen.MyAccount.name) {
            MyAccountScreen(
                viewModel = myAccountViewModel,
                navigateToScreen = { route -> navController.navigate(route) }
            )
        }
        composable(route = RmcScreen.EditMyAccount.name) {
            EditMyAccountScreen(
                viewModel = editMyAccountViewModel,
                navigateToScreen = { route -> navController.navigate(route) }
            )
        }
        composable(route = RmcScreen.RmcTestScreen.name) {
            RepositoryTestScreen(
                viewModel = repositoryTestViewModel
            )
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