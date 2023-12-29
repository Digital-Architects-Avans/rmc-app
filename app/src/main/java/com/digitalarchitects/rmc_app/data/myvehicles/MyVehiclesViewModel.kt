package com.digitalarchitects.rmc_app.data.myvehicles

import androidx.lifecycle.ViewModel
import com.digitalarchitects.rmc_app.app.RmcScreen
import com.digitalarchitects.rmc_app.domain.repo.VehicleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MyVehiclesViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository
) : ViewModel() {
    private val _navigateToScreen = MutableStateFlow<RmcScreen?>(null)
    val navigateToScreen = _navigateToScreen.asStateFlow()

    private val _state = MutableStateFlow(MyVehiclesUIState())
    private val _uiState = _state
    val uiState: StateFlow<MyVehiclesUIState> get() = _uiState.asStateFlow()

    fun onEvent(event: MyVehiclesUIEvent) {
        when (event) {
            is MyVehiclesUIEvent.NavigateUpButtonClicked -> {
                _navigateToScreen.value = RmcScreen.MyAccount
            }
            is MyVehiclesUIEvent.NewVehicleButtonClicked -> {
                _navigateToScreen.value = RmcScreen.RegisterVehicle
            }
        }
    }
}