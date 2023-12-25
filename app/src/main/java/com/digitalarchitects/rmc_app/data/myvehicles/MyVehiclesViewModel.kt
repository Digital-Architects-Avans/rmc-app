package com.digitalarchitects.rmc_app.data.myvehicles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalarchitects.rmc_app.app.RmcScreen
import com.digitalarchitects.rmc_app.data.editmyaccount.EditMyAccountUIState
import com.digitalarchitects.rmc_app.model.Vehicle
import com.digitalarchitects.rmc_app.room.VehicleDao
import com.digitalarchitects.rmc_app.room.VehicleTable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class MyVehiclesViewModel(
    private val vehicleDao: VehicleDao
) : ViewModel() {
    private val _vehicleList = MutableLiveData<List<VehicleTable>>()
    val vehicleList: LiveData<List<VehicleTable>> get() = _vehicleList

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

            is MyVehiclesUIEvent.ShowVehicles -> {
                viewModelScope.launch {
                    // Collect the Flow to get updates when data changes
                    vehicleDao.getVehiclesOrderedById().collect { getVehicles ->
                        _vehicleList.value = getVehicles
                    }
                }
            }
        }
    }
}