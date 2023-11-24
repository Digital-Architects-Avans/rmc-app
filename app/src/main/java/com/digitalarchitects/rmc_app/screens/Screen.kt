package com.digitalarchitects.rmc_app.screens

sealed class Screen(val route: String) {
    object Login: Screen(route = "login_screen")
    object Register: Screen(route = "register_screen")
    object TermsAndConditions: Screen(route = "termsAndConditions_screen")
}
