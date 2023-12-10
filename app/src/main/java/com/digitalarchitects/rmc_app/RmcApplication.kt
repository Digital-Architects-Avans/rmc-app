package com.digitalarchitects.rmc_app

import android.app.Application
import com.digitalarchitects.rmc_app.data.AppContainer
import com.digitalarchitects.rmc_app.data.DefaultAppContainer

class RmcApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}