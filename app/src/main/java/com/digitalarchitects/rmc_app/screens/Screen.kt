package com.digitalarchitects.rmc_app.screens

sealed class Screen(val route: String) {
    object Login: Screen(route = "login_screen")
    object Register: Screen(route = "register_screen")
    object TermsAndConditions: Screen(route = "termsAndConditions_screen")
    object MyAccount: Screen(route = "my_account_screen")
    object RentOutMyCar: Screen(route = "rent_out_my_car")
    object MyVehicles: Screen(route = "my_vehicles")

}
