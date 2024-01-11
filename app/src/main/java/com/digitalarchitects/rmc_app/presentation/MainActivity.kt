package com.digitalarchitects.rmc_app.presentation

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.digitalarchitects.rmc_app.ui.theme.RmcAppTheme
import com.google.android.libraries.places.api.Places
import dagger.hilt.android.AndroidEntryPoint

const val GOOGLE_API_KEY_NAME = "com.google.android.geo.API_KEY"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiKey = applicationContext.packageManager.getApplicationInfo(
            packageName,
            PackageManager.GET_META_DATA
        ).metaData.getString(GOOGLE_API_KEY_NAME)

        Places.initialize(applicationContext, apiKey)

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

