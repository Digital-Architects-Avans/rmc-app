package com.digitalarchitects.rmc_app.presentation.screens.editmyvehicle

import androidx.lifecycle.ViewModel
import com.digitalarchitects.rmc_app.data.remote.dto.vehicle.UpdateVehicleDTO
import com.digitalarchitects.rmc_app.domain.model.EngineType
import com.digitalarchitects.rmc_app.domain.repo.VehicleRepository
import com.digitalarchitects.rmc_app.presentation.RmcScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditMyVehicleViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository,
) : ViewModel() {
    private val _navigateToScreen = MutableStateFlow<RmcScreen?>(null)
    val navigateToScreen = _navigateToScreen.asStateFlow()

    private val _state = MutableStateFlow(EditMyVehicleUIState())
    private val _uiState = _state
    val uiState: StateFlow<EditMyVehicleUIState> get() = _uiState.asStateFlow()

    fun onEvent(event: EditMyVehicleUIEvent) {
        when (event) {
            is EditMyVehicleUIEvent.CancelEditMyVehicleButtonClicked -> {
                _navigateToScreen.value = RmcScreen.MyVehicles
            }

            is EditMyVehicleUIEvent.ConfirmEditMyVehicleButtonClicked -> {
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

                        val updatedVehicle = UpdateVehicleDTO(
                            userId = userId,
                            brand = brand,
                            model = model,
                            year = year,
                            vehicleClass = vehicleClass,
                            engineType = engineType,
                            licensePlate = licensePlate,
                            imgLink = imgLink,
                            latitude = latitude,
                            longitude = longitude,
                            price = price,
                            availability = availability
                        )
                        //vehicleRepository.updateVehicle(vehicle = updatedVehicle)
                    }
                    _navigateToScreen.value = RmcScreen.MyVehicles

                } catch (e: Exception) {
                    // Handle the exception or log an error
                    // TODO ERROR MESSAGE
                }
            }

            is EditMyVehicleUIEvent.DeleteMyVehicleButtonClicked -> {
                _navigateToScreen.value = RmcScreen.MyAccount
            }

            is EditMyVehicleUIEvent.NavigateUpButtonClicked -> {
                _navigateToScreen.value = RmcScreen.MyVehicles
            }

            is EditMyVehicleUIEvent.SetAvailability -> {
                _state.update {
                    it.copy(
                        availability = event.availability
                    )
                }
            }

            is EditMyVehicleUIEvent.SetBrand -> {
                _state.update {
                    it.copy(
                        brand = event.brand
                    )
                }
            }

            is EditMyVehicleUIEvent.SetEngineType -> {
                _state.update {
                    it.copy(
                        engineType = event.engineType
                    )
                }
            }

            is EditMyVehicleUIEvent.SetId -> {
                _state.update {
                    it.copy(
                        id = event.id
                    )
                }
            }

            is EditMyVehicleUIEvent.SetImgLink -> {
                _state.update {
                    it.copy(
                        imgLink = event.imgLink.toInt()
                    )
                }
            }

            is EditMyVehicleUIEvent.SetLatitude -> {
                _state.update {
                    it.copy(
                        latitude = event.latitude
                    )
                }
            }

            is EditMyVehicleUIEvent.SetLicensePlate -> {
                _state.update {
                    it.copy(
                        licensePlate = event.licensePlate
                    )
                }
            }

            is EditMyVehicleUIEvent.SetLongitude -> {
                _state.update {
                    it.copy(
                        longitude = event.longitude
                    )
                }
            }

            is EditMyVehicleUIEvent.SetModel -> {
                _state.update {
                    it.copy(
                        model = event.model
                    )
                }
            }

            is EditMyVehicleUIEvent.SetPrice -> {
                _state.update {
                    it.copy(
                        price = event.price
                    )
                }
            }

            is EditMyVehicleUIEvent.SetUserId -> {
                _state.update {
                    it.copy(
                        userId = event.userId
                    )
                }
            }

            is EditMyVehicleUIEvent.SetVehicleClass -> {
                _state.update {
                    it.copy(
                        vehicleClass = event.vehicleClass
                    )
                }
            }

            is EditMyVehicleUIEvent.SetYear -> {
                _state.update {
                    it.copy(
                        year = event.year
                    )
                }
            }

            is EditMyVehicleUIEvent.ShowVehicle -> {
                try {
                    val scope = CoroutineScope(Dispatchers.IO)
                    scope.launch {
                        val getVehicle = vehicleRepository.getVehicleById("1")
                        val id = getVehicle?.vehicleId
                        val userId = getVehicle?.userId
                        val brand = getVehicle?.brand
                        val model = getVehicle?.model
                        val year = getVehicle?.year
                        val vehicleClass = getVehicle?.vehicleClass
                        val engineType = getVehicle?.engineType
                        val licensePlate = getVehicle?.licensePlate
                        val imgLink = getVehicle?.imgLink
                        val latitude = getVehicle?.latitude
                        val longitude = getVehicle?.longitude
                        val price = getVehicle?.price
                        val availability = getVehicle?.availability

                       // TODO ("Fix after merge")
//                        _state.value = _state.value.copy(
//                            id = id,
//                            userId = userId,
//                            brand = brand,
//                            model = model,
//                            year = year,
//                            vehicleClass = vehicleClass,
//                            engineType = engineType,
//                            licensePlate = licensePlate,
//                            imgLink = imgLink,
//                            latitude = latitude,
//                            longitude = longitude,
//                            price = price,
//                            availability = availability
//                        )
                    }


                } catch (e: Exception) {
                    // Handle the exception or log an error
                    // TODO ERROR MESSAGE
                }
            }

            is EditMyVehicleUIEvent.EngineTypeBEVButtonClicked -> {
                _state.update {
                    it.copy(
                        engineType = EngineType.BEV
                    )
                }
            }

            is EditMyVehicleUIEvent.EngineTypeFCEVButtonClicked -> {
                _state.update {
                    it.copy(
                        engineType = EngineType.FCEV
                    )
                }
            }

            is EditMyVehicleUIEvent.EngineTypeICEButtonClicked -> {
                _state.update {
                    it.copy(
                        engineType = EngineType.ICE
                    )
                }
            }

            is EditMyVehicleUIEvent.AvailabilityToggleButtonClicked -> {
                _state.update {
                    it.copy(
                        availability = !it.availability
                    )
                }
            }
        }
    }
}