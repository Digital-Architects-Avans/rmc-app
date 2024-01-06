package com.digitalarchitects.rmc_app.presentation.screens.editmyvehicle

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalarchitects.rmc_app.data.remote.dto.vehicle.UpdateVehicleDTO
import com.digitalarchitects.rmc_app.domain.model.EngineType
import com.digitalarchitects.rmc_app.domain.repo.VehicleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EditMyVehicleViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditMyVehicleUIState())
    val uiState: StateFlow<EditMyVehicleUIState> get() = _uiState.asStateFlow()

    private val _vehicleUpdated = MutableStateFlow(false)
    val vehicleUpdated: StateFlow<Boolean> = _vehicleUpdated.asStateFlow()

    fun fetchVehicleDetails(vehicleId: String) {
        viewModelScope.launch {
            try {
                val vehicle = vehicleRepository.getVehicleById(vehicleId)
                if (vehicle == null) {
                    Log.d("EditMyVehicleViewModel", "vehicle is null")
                } else {
                    _uiState.update {
                        it.copy(
                            vehicleId = vehicle.vehicleId,
                            userId = vehicle.userId,
                            brand = vehicle.brand,
                            model = vehicle.model,
                            year = vehicle.year,
                            vehicleClass = vehicle.vehicleClass,
                            engineType = vehicle.engineType,
                            licensePlate = vehicle.licensePlate,
                            imgLink = vehicle.imgLink,
                            latitude = vehicle.latitude,
                            longitude = vehicle.longitude,
                            price = vehicle.price,
                            availability = vehicle.availability
                        )
                    }
                }
            } catch (e: Exception) {
                Log.d("EditMyVehicleViewModel", "error: $e")
            }
        }
    }

    fun onEvent(event: EditMyVehicleUIEvent) {
        when (event) {
            is EditMyVehicleUIEvent.SetAvailability -> {
                _uiState.update {
                    it.copy(
                        availability = event.availability
                    )
                }
            }

            is EditMyVehicleUIEvent.SetBrand -> {
                _uiState.update {
                    it.copy(
                        brand = event.brand
                    )
                }
            }

            is EditMyVehicleUIEvent.SetEngineType -> {
                _uiState.update {
                    it.copy(
                        engineType = event.engineType
                    )
                }
            }

            is EditMyVehicleUIEvent.SetId -> {
                _uiState.update {
                    it.copy(
                        vehicleId = event.id
                    )
                }
            }

            is EditMyVehicleUIEvent.SetImgLink -> {
                _uiState.update {
                    it.copy(
                        imgLink = event.imgLink.toInt()
                    )
                }
            }

            is EditMyVehicleUIEvent.SetLatitude -> {
                _uiState.update {
                    it.copy(
                        latitude = event.latitude
                    )
                }
            }

            is EditMyVehicleUIEvent.SetLicensePlate -> {
                _uiState.update {
                    it.copy(
                        licensePlate = event.licensePlate
                    )
                }
            }

            is EditMyVehicleUIEvent.SetLongitude -> {
                _uiState.update {
                    it.copy(
                        longitude = event.longitude
                    )
                }
            }

            is EditMyVehicleUIEvent.SetModel -> {
                _uiState.update {
                    it.copy(
                        model = event.model
                    )
                }
            }

            is EditMyVehicleUIEvent.SetPrice -> {
                _uiState.update {
                    it.copy(
                        price = event.price
                    )
                }
            }

            is EditMyVehicleUIEvent.SetUserId -> {
                _uiState.update {
                    it.copy(
                        userId = event.userId
                    )
                }
            }

            is EditMyVehicleUIEvent.SetVehicleClass -> {
                _uiState.update {
                    it.copy(
                        vehicleClass = event.vehicleClass
                    )
                }
            }

            is EditMyVehicleUIEvent.SetYear -> {
                _uiState.update {
                    it.copy(
                        year = event.year
                    )
                }
            }

            is EditMyVehicleUIEvent.EngineTypeBEVButtonClicked -> {
                _uiState.update {
                    it.copy(
                        engineType = EngineType.BEV
                    )
                }
            }

            is EditMyVehicleUIEvent.EngineTypeFCEVButtonClicked -> {
                _uiState.update {
                    it.copy(
                        engineType = EngineType.FCEV
                    )
                }
            }

            is EditMyVehicleUIEvent.EngineTypeICEButtonClicked -> {
                _uiState.update {
                    it.copy(
                        engineType = EngineType.ICE
                    )
                }
            }

            is EditMyVehicleUIEvent.AvailabilityToggleButtonClicked -> {
                _uiState.update {
                    it.copy(
                        availability = !it.availability
                    )
                }
            }

            is EditMyVehicleUIEvent.ConfirmEditMyVehicleButtonClicked -> {
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

                viewModelScope.launch {
                    try {
                        withContext(Dispatchers.IO) {
                            vehicleRepository.updateVehicle(uiState.value.vehicleId, updatedVehicle)

                            withContext(Dispatchers.Main) {
                                _vehicleUpdated.value = true
                            }
                        }
                        Log.d("RegisterVehicleViewModel", "Created vehicle successfully")

                    } catch (e: Exception) {
                        _vehicleUpdated.value = false
                        Log.d("RegisterVehicleViewModel", "Error creating vehicle: $e")
                    }
                }
            }
            is EditMyVehicleUIEvent.ResetVehicleUpdated -> {
                _vehicleUpdated.value = false
            }

        }
    }
}