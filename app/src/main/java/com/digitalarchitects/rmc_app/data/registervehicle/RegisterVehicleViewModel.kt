package com.digitalarchitects.rmc_app.data.registervehicle

import androidx.lifecycle.ViewModel
import com.digitalarchitects.rmc_app.app.RmcScreen
import com.digitalarchitects.rmc_app.room.VehicleDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class RegisterVehicleViewModel(
    private val vehicleDao: VehicleDao
) : ViewModel() {
    private val _navigateToScreen = MutableStateFlow<RmcScreen?>(null)
    val navigateToScreen = _navigateToScreen.asStateFlow()
    fun onEvent(event: RegisterVehicleUIEvent) {
        when (event) {
            is RegisterVehicleUIEvent.NavigateUpButtonClicked -> {
                _navigateToScreen.value = RmcScreen.MyVehicles
            }

            is RegisterVehicleUIEvent.RegisterVehicleButtonClicked -> {
                //TODO ADD VEHICLE LOGIC
                _navigateToScreen.value = RmcScreen.MyVehicles
            }
        }
    }
}