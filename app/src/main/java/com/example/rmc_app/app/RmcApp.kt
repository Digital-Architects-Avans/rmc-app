package com.example.rmc_app.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.rmc_app.navigation.RmcAppRouter
import com.example.rmc_app.navigation.Screen
import com.example.rmc_app.screens.LoginScreen
import com.example.rmc_app.screens.RegisterScreen
import com.example.rmc_app.screens.TermsAndConditionsScreen
import com.example.rmc_app.ui.theme.RmcappTheme


@Preview(showBackground = true)
@Composable
fun RmcApp() {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Crossfade(targetState = RmcAppRouter.currentScreen, label = "") { currentState ->
            when (currentState.value) {
                is Screen.RegisterScreen -> {
                    RegisterScreen()
                }
                is Screen.TermsAndConditionsScreen -> {
                    TermsAndConditionsScreen()
                }
                is Screen.LoginScreen -> {
                    LoginScreen()
                }
            }
        }
    }
}


@Preview
@Composable
fun Preview() {
    RmcappTheme {
        RmcApp()
//        VehicleList()
//        RegisterSection()
    }
}