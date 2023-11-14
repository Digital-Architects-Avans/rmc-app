package com.example.rmc_app.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.rmc_app.screens.RegisterScreen
import com.example.rmc_app.ui.theme.RmcappTheme


@Preview(showBackground = true)
@Composable
fun RmcApp(modifier: Modifier = Modifier) {
    RegisterScreen()
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