package com.digitalarchitects.rmc_app.data.rentacar

import androidx.lifecycle.ViewModel
import com.digitalarchitects.rmc_app.app.RmcScreen
import com.digitalarchitects.rmc_app.data.editmyaccount.EditMyAccountUIState
import com.digitalarchitects.rmc_app.data.myaccount.MyAccountUIEvent
import com.digitalarchitects.rmc_app.data.myaccount.MyAccountUIState
import com.digitalarchitects.rmc_app.room.UserDao
import com.digitalarchitects.rmc_app.room.VehicleDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class RentACarViewModel(
    private val vehicleDao: VehicleDao,
) : ViewModel() {
    private val _navigateToScreen = MutableStateFlow<RmcScreen?>(null)
    val navigateToScreen = _navigateToScreen.asStateFlow()

    private val _state = MutableStateFlow(RentACarUIState())
    private val _uiState = _state
    val uiState: StateFlow<RentACarUIState> get() = _uiState.asStateFlow()

    fun onEvent(event: RentACarUIEvent) {
        when (event) {
            RentACarUIEvent.MyAccountButtonClicked -> {
                _navigateToScreen.value = RmcScreen.MyAccount
            }
            RentACarUIEvent.MyRentalsButtonClicked -> {
                _navigateToScreen.value = RmcScreen.MyRentals
            }
            RentACarUIEvent.RentOutMyVehicleButtonClicked -> {
                _navigateToScreen.value = RmcScreen.RentOutMyCar
            }
            RentACarUIEvent.SearchButtonClicked -> {
                _navigateToScreen.value = RmcScreen.Search
            }
        }
    }
}