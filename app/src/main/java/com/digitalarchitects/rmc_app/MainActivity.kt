package com.digitalarchitects.rmc_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.digitalarchitects.rmc_app.app.RmcApp
import com.digitalarchitects.rmc_app.ui.theme.RmcAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            false
        }
        setContent {
            RmcAppTheme {
                RmcApp()
            }
        }
    }
}
