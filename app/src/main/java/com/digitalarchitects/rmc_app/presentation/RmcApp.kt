package com.digitalarchitects.rmc_app.presentation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.presentation.screens.editmyaccount.EditMyAccountScreen
import com.digitalarchitects.rmc_app.presentation.screens.editmyaccount.EditMyAccountViewModel
import com.digitalarchitects.rmc_app.presentation.screens.editmyvehicle.EditMyVehicleScreen
import com.digitalarchitects.rmc_app.presentation.screens.editmyvehicle.EditMyVehicleViewModel
import com.digitalarchitects.rmc_app.presentation.screens.login.LoginScreen
import com.digitalarchitects.rmc_app.presentation.screens.login.LoginViewModel
import com.digitalarchitects.rmc_app.presentation.screens.myaccount.MyAccountScreen
import com.digitalarchitects.rmc_app.presentation.screens.myaccount.MyAccountViewModel
import com.digitalarchitects.rmc_app.presentation.screens.myrentals.MyRentalsScreen
import com.digitalarchitects.rmc_app.presentation.screens.myrentals.MyRentalsViewModel
import com.digitalarchitects.rmc_app.presentation.screens.myvehicles.MyVehiclesScreen
import com.digitalarchitects.rmc_app.presentation.screens.myvehicles.MyVehiclesViewModel
import com.digitalarchitects.rmc_app.presentation.screens.register.RegisterScreen
import com.digitalarchitects.rmc_app.presentation.screens.register.RegisterViewModel
import com.digitalarchitects.rmc_app.presentation.screens.registervehicle.RegisterVehicleScreen
import com.digitalarchitects.rmc_app.presentation.screens.registervehicle.RegisterVehicleViewModel
import com.digitalarchitects.rmc_app.presentation.screens.rentacar.RentACarScreen
import com.digitalarchitects.rmc_app.presentation.screens.rentacar.RentACarViewModel
import com.digitalarchitects.rmc_app.presentation.screens.rentoutmycar.RentOutMyCarScreen
import com.digitalarchitects.rmc_app.presentation.screens.rentoutmycar.RentOutMyCarViewModel
import com.digitalarchitects.rmc_app.presentation.screens.repositorytest.RepositoryTestScreen
import com.digitalarchitects.rmc_app.presentation.screens.repositorytest.RepositoryTestViewModel
import com.digitalarchitects.rmc_app.presentation.screens.search.SearchScreen
import com.digitalarchitects.rmc_app.presentation.screens.search.SearchViewModel
import com.digitalarchitects.rmc_app.presentation.screens.termsandconditions.TermsAndConditionsScreen
import com.digitalarchitects.rmc_app.presentation.screens.termsandconditions.TermsAndConditionsViewModel
import com.digitalarchitects.rmc_app.presentation.screens.welcome.WelcomeScreen
import com.digitalarchitects.rmc_app.presentation.screens.welcome.WelcomeViewModel
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
    EditMyVehicle(title = R.string.screen_title_edit_vehicle),
    RmcTestScreen(title = R.string.rmcTestScreenTitle),
    RmcLocationTestScreen(title = R.string.rmcTestScreenTitle)
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
    val registerVehicleViewModel: RegisterVehicleViewModel = hiltViewModel()
    val myRentalsViewModel: MyRentalsViewModel = hiltViewModel()
    val editMyVehicleViewModel: EditMyVehicleViewModel = hiltViewModel()
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
            MyRentalsScreen(
                viewModel = myRentalsViewModel,
                navigateToScreen = { route -> navController.navigate(route) }
            )
        }
        composable(route = RmcScreen.RentOutMyCar.name) {

            RentOutMyCarScreen(
                viewModel = rentOutMyCarViewModel,
                navigateToScreen = { route -> navController.navigate(route) }
            )
        }
        composable(route = RmcScreen.MyVehicles.name) {
            MyVehiclesScreen(
                viewModel = myVehiclesViewModel,
                navigateToScreen = { route -> navController.navigate(route) },
                navigateToEditVehicle = { route, vehicleId ->
                    navController.navigate("$route/$vehicleId")
                }
            )
        }
        composable(route = RmcScreen.RegisterVehicle.name) {
            RegisterVehicleScreen(
                viewModel = registerVehicleViewModel,
                navigateToScreen = { route -> navController.navigate(route) }
            )
        }
        composable(
            route = "${RmcScreen.EditMyVehicle.name}/{vehicleId}",
            arguments = listOf(navArgument("vehicleId") { type = NavType.StringType })
        ) { backStackEntry ->
            val vehicleId = backStackEntry.arguments?.getString("vehicleId")
            EditMyVehicleScreen(
                viewModel = editMyVehicleViewModel,
                navigateToScreen = { route -> navController.navigate(route) },
                vehicleId = vehicleId
            )
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