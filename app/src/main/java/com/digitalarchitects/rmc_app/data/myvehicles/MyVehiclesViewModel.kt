package com.digitalarchitects.rmc_app.data.myvehicles

import androidx.lifecycle.ViewModel
import com.digitalarchitects.rmc_app.app.RmcScreen
import com.digitalarchitects.rmc_app.room.VehicleDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class MyVehiclesViewModel(
    private val vehicleDao: VehicleDao
) : ViewModel() {
    private val _navigateToScreen = MutableStateFlow<RmcScreen?>(null)
    val navigateToScreen = _navigateToScreen.asStateFlow()
    fun onEvent(event: MyVehiclesUIEvent) {
        when (event) {
            MyVehiclesUIEvent.NavigateUpButtonClicked -> {
                _navigateToScreen.value = RmcScreen.MyAccount
            }
            MyVehiclesUIEvent.NewVehicleButtonClicked -> {
                _navigateToScreen.value = RmcScreen.RegisterVehicle
            }
        }
    }
}