package com.digitalarchitects.rmc_app.data.myrentals

import androidx.lifecycle.ViewModel
import com.digitalarchitects.rmc_app.app.RmcScreen
import com.digitalarchitects.rmc_app.room.VehicleDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyRentalsViewModel(
    private val vehicleDao: VehicleDao
) : ViewModel() {
    private val _navigateToScreen = MutableStateFlow<RmcScreen?>(null)
    val navigateToScreen = _navigateToScreen.asStateFlow()

    private val _state = MutableStateFlow(MyRentalsUIState())
    private val _uiState = _state
    val uiState: StateFlow<MyRentalsUIState> get() = _uiState.asStateFlow()

    fun onEvent(event: MyRentalsUIEvent) {
        when (event) {
            is MyRentalsUIEvent.NavigateUpButtonClicked -> {
                _navigateToScreen.value = RmcScreen.RentACar
            }
        }
    }
}
