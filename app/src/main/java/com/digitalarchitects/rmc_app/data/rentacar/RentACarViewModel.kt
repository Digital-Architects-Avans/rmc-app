package com.digitalarchitects.rmc_app.data.rentacar

import androidx.lifecycle.ViewModel
import com.digitalarchitects.rmc_app.dummyDTO.DummyVehicleDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RentACarViewModel : ViewModel() {

    // Rent A Car UI state
    private val _uiState = MutableStateFlow(RentACarUIState())
    val uiState: StateFlow<RentACarUIState> = _uiState.asStateFlow()

    // Get search settings
    // Get all vehicles
    // Filter all vehicles on search settings
    // pass vehicleList to screen
    val listOfVehicles = DummyVehicleDTO()

    fun onEvent(event: RentACarUIEvent) {
        when (event) {
            is RentACarUIEvent.ToggleListView -> {
                toggleVehicleList()
            }
            is RentACarUIEvent.ToggleDetailsView -> {
                toggleVehicleDetails()
            }
        }
    }

    private fun toggleVehicleList() {
        val isListVisible = _uiState.value.showVehicleList
        _uiState.value = _uiState.value.copy(
            showVehicleList = !isListVisible
        )
    }

    private fun toggleVehicleDetails() {
        val isDetailsVisible = _uiState.value.showVehicleDetails
        _uiState.value = _uiState.value.copy(
            showVehicleDetails = !isDetailsVisible
        )
    }
}