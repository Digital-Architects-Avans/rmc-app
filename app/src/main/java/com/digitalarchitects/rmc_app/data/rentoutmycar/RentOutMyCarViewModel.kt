package com.digitalarchitects.rmc_app.data.rentoutmycar

import androidx.lifecycle.ViewModel
import com.digitalarchitects.rmc_app.app.RmcScreen
import com.digitalarchitects.rmc_app.room.RentalDao
import com.digitalarchitects.rmc_app.room.VehicleDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class RentOutMyCarViewModel(
    private val vehicleDao: VehicleDao,
    private val rentalDao: RentalDao

) : ViewModel() {
    private val _navigateToScreen = MutableStateFlow<RmcScreen?>(null)
    val navigateToScreen = _navigateToScreen.asStateFlow()

    private val _state = MutableStateFlow(RentOutMyCarUIState())
    private val _uiState = _state
    val uiState: StateFlow<RentOutMyCarUIState> get() = _uiState.asStateFlow()

    fun onEvent(event: RentOutMyCarUIEvent) {
        when (event) {
            RentOutMyCarUIEvent.NavigateUpButtonClicked -> {
                _navigateToScreen.value = RmcScreen.RentACar
            }
        }
    }
}