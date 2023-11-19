package com.example.rmc_app.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {

    object RegisterScreen : Screen()
    object TermsAndConditionsScreen : Screen()
    object LoginScreen : Screen()
}


object RmcAppRouter {

    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.RegisterScreen)

    fun navigateTo(destination : Screen){
        currentScreen.value = destination
    }


}