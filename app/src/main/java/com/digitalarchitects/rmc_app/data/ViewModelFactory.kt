package com.digitalarchitects.rmc_app.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.digitalarchitects.rmc_app.data.register.RegisterViewModel
import com.digitalarchitects.rmc_app.data.welcome.WelcomeViewModel
import com.digitalarchitects.rmc_app.room.RmcRoomDatabase

class ViewModelFactory(
    private val db: RmcRoomDatabase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)) {
            return WelcomeViewModel() as T
        }
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel() as T
        }
        // Add other ViewModel cases if needed
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}