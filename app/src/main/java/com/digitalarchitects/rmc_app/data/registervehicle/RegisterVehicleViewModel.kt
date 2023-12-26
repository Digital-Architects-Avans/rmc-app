package com.digitalarchitects.rmc_app.data.registervehicle

import androidx.lifecycle.ViewModel
import com.digitalarchitects.rmc_app.app.RmcScreen
import com.digitalarchitects.rmc_app.model.EngineType
import com.digitalarchitects.rmc_app.room.VehicleDao
import com.digitalarchitects.rmc_app.room.VehicleTable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RegisterVehicleViewModel(
    private val vehicleDao: VehicleDao
) : ViewModel() {
    private val _navigateToScreen = MutableStateFlow<RmcScreen?>(null)
    val navigateToScreen = _navigateToScreen.asStateFlow()

    private val _state = MutableStateFlow(RegisterVehicleUIState())
    private val _uiState = _state
    val uiState: StateFlow<RegisterVehicleUIState> get() = _uiState.asStateFlow()

    fun onEvent(event: RegisterVehicleUIEvent) {
        when (event) {
            is RegisterVehicleUIEvent.NavigateUpButtonClicked -> {
                _navigateToScreen.value = RmcScreen.MyVehicles
            }

            is RegisterVehicleUIEvent.AvailabilityToggleButtonClicked -> {
                _state.update {
                    it.copy(
                        availability = !it.availability
                    )
                }
            }

            is RegisterVehicleUIEvent.ConfirmRegisterVehicleButtonClicked -> {
                try {
                    val scope = CoroutineScope(Dispatchers.IO)
                    scope.launch {
                        val id = _uiState.value.id
                        val userId = _uiState.value.userId
                        val brand = _uiState.value.brand
                        val model = _uiState.value.model
                        val year = _uiState.value.year
                        val vehicleClass = _uiState.value.vehicleClass
                        val engineType = _uiState.value.engineType
                        val licensePlate = _uiState.value.licensePlate
                        val imgLink = _uiState.value.imgLink
                        val latitude = _uiState.value.latitude
                        val longitude = _uiState.value.longitude
                        val price = _uiState.value.price
                        val availability = _uiState.value.availability

                        val newVehicle = VehicleTable(
                            id = id,
                            userId = userId,
                            brand = brand,
                            model = model,
                            year = year.toInt(),
                            vehicleClass = vehicleClass,
                            engineType = engineType,
                            licensePlate = licensePlate,
                            imgLink = imgLink.toInt(),
                            latitude = latitude.toFloat(),
                            longitude = longitude.toFloat(),
                            price = price.toDouble(),
                            availability = availability
                        )
                        vehicleDao.insertVehicle(vehicle = newVehicle)
                    }
                    _navigateToScreen.value = RmcScreen.MyVehicles

                } catch (e: Exception) {
                    // Handle the exception or log an error
                    // TODO ERROR MESSAGE
                }
            }

            is RegisterVehicleUIEvent.EngineTypeBEVButtonClicked -> {
                _state.update {
                    it.copy(
                        engineType = EngineType.BEV
                    )
                }
            }

            is RegisterVehicleUIEvent.EngineTypeFCEVButtonClicked -> {
                _state.update {
                    it.copy(
                        engineType = EngineType.FCEV
                    )
                }
            }

            is RegisterVehicleUIEvent.EngineTypeICEButtonClicked -> {
                _state.update {
                    it.copy(
                        engineType = EngineType.ICE
                    )
                }
            }

            is RegisterVehicleUIEvent.SetAvailability -> {
                _state.update {
                    it.copy(
                        availability = event.availability
                    )
                }
            }

            is RegisterVehicleUIEvent.SetBrand -> {
                _state.update {
                    it.copy(
                        brand = event.brand
                    )
                }
            }

            is RegisterVehicleUIEvent.SetEngineType -> {
                _state.update {
                    it.copy(
                        engineType = event.engineType
                    )
                }
            }

            is RegisterVehicleUIEvent.SetId -> {
                _state.update {
                    it.copy(
                        id = event.id
                    )
                }
            }

            is RegisterVehicleUIEvent.SetImgLink -> {
                _state.update {
                    it.copy(
                        imgLink = event.imgLink
                    )
                }
            }

            is RegisterVehicleUIEvent.SetLatitude -> {
                _state.update {
                    it.copy(
                        latitude = event.latitude.toString()
                    )
                }
            }

            is RegisterVehicleUIEvent.SetLicensePlate -> {
                _state.update {
                    it.copy(
                        licensePlate = event.licensePlate
                    )
                }
            }

            is RegisterVehicleUIEvent.SetLongitude -> {
                _state.update {
                    it.copy(
                        longitude = event.longitude.toString()
                    )
                }
            }

            is RegisterVehicleUIEvent.SetModel -> {
                _state.update {
                    it.copy(
                        model = event.model
                    )
                }
            }

            is RegisterVehicleUIEvent.SetPrice -> {
                _state.update {
                    it.copy(
                        price = event.price.toString()
                    )
                }
            }

            is RegisterVehicleUIEvent.SetUserId -> {
                _state.update {
                    it.copy(
                        userId = event.userId
                    )
                }
            }

            is RegisterVehicleUIEvent.SetVehicleClass -> {
                _state.update {
                    it.copy(
                        vehicleClass = event.vehicleClass
                    )
                }
            }

            is RegisterVehicleUIEvent.SetYear -> {
                _state.update {
                    it.copy(
                        year = event.year.toString()
                    )
                }
            }
        }
    }
}