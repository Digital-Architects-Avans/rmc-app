package com.digitalarchitects.rmc_app.presentation.screens.myvehicles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.digitalarchitects.rmc_app.data.local.LocalVehicle
import com.digitalarchitects.rmc_app.domain.repo.VehicleRepository
import com.digitalarchitects.rmc_app.presentation.RmcScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MyVehiclesViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository
) : ViewModel() {
    private val _vehicleList = MutableLiveData<List<LocalVehicle>>()
    val vehicleList: LiveData<List<LocalVehicle>> get() = _vehicleList

    private val _navigateToScreen = MutableStateFlow<RmcScreen?>(null)
    val navigateToScreen = _navigateToScreen.asStateFlow()

    private val _state = MutableStateFlow(MyVehiclesUIState())
    private val _uiState = _state
    val uiState: StateFlow<MyVehiclesUIState> get() = _uiState.asStateFlow()

    fun onEvent(event: MyVehiclesUIEvent) {
        when (event) {

            is MyVehiclesUIEvent.NewVehicleButtonClicked -> {
                _navigateToScreen.value = RmcScreen.RegisterVehicle
            }

            is MyVehiclesUIEvent.ShowVehicles -> {
//                viewModelScope.launch {
//                    // Collect the Flow to get updates when data changes
//                    vehicleRepository.getAllVehicles().collect { getVehicles ->
//                        _vehicleList.value = getVehicles
//                    }
            }
        }
    }
}