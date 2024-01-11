package com.digitalarchitects.rmc_app.presentation.screens.registervehicle

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalarchitects.rmc_app.data.di.IoDispatcher
import com.digitalarchitects.rmc_app.data.remote.dto.vehicle.CreateVehicleDTO
import com.digitalarchitects.rmc_app.domain.model.AddressItem
import com.digitalarchitects.rmc_app.domain.model.EngineType
import com.digitalarchitects.rmc_app.domain.model.PlaceItem
import com.digitalarchitects.rmc_app.domain.repo.PlacesRepository
import com.digitalarchitects.rmc_app.domain.repo.UserRepository
import com.digitalarchitects.rmc_app.domain.repo.VehicleRepository
import com.digitalarchitects.rmc_app.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterVehicleViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository,
    private val userRepository: UserRepository,
    private val placesRepository: PlacesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterVehicleUIState())
    val uiState: StateFlow<RegisterVehicleUIState> get() = _uiState.asStateFlow()

    private val _vehicleUpdated = MutableStateFlow(false)
    val vehicleUpdated: StateFlow<Boolean> = _vehicleUpdated.asStateFlow()

    private val _address: MutableStateFlow<AddressItem> = MutableStateFlow(AddressItem())
    val address = _address.asStateFlow()

    private val _placePredictions: MutableStateFlow<List<PlaceItem>> = MutableStateFlow(arrayListOf())
    val placePredictions = _placePredictions.asStateFlow()

    private val _showProgressBar: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showProgressBar = _showProgressBar.asStateFlow()

    init {
        getUserId()
    }

    // SEt the uiState.userId so we can attach it to the vehicle
    private fun getUserId() {
        viewModelScope.launch(dispatcher) {
            try {
                val userId = userRepository.getCurrentUserIdFromDataStore()
                withContext(Dispatchers.Main) {
                    _uiState.update {
                        it.copy(
                            userId = userId!!
                        )
                    }
                }
            } catch (e: Exception) {
                Log.d("RegisterVehicleViewModel", "error: $e")
            }
        }
    }

    private fun getPlacePredictions(query: String) {
        Log.d("RegisterVehicleViewModel", "streetAddress: ${address.value.streetAddress}")
        viewModelScope.launch(dispatcher) {

            when (val placePredictionsResult = placesRepository.getPlacePredictions(query)) {
                is Result.Success -> {
                    val placePredictions = placePredictionsResult.data

                    _placePredictions.value = placePredictions
                    Log.d("RegisterVehicleViewModel", "placePredictions: $placePredictions")
                }

                is Result.Error -> {
                    Log.e(
                        "RegisterVehicleViewModel",
                        "An error occurred when retrieving the predictions for $query",
                        placePredictionsResult.exception
                    )
                }

                else -> {}
            }
        }
    }

    private suspend fun setLocationPrediction(placeItem: PlaceItem) {
        Log.d("RegisterVehicleViewModel", "placeItem: $placeItem")

        viewModelScope.launch(dispatcher) {
            when (val addressResult = placesRepository.getLocationFromPlace(placeItem.id!!)) {

                is Result.Success -> {
                    val addressFromPlace = addressResult.data
                    if (addressFromPlace != null) {
                        _address.value = addressFromPlace
                    }
                    Log.d("RegisterVehicleViewModel", "addressFromPlace: $addressFromPlace")
                    clearPredictions()
                }

                is Result.Error -> {
                    Log.e(
                        "RegisterVehicleViewModel",
                        "An error occurred when retrieving the address from Place  ${placeItem.id}",
                        addressResult.exception
                    )
                }
                else -> {}
            }
        }
    }

    fun onLocationAutoCompleteClear() {
        Log.d("RegisterVehicleViewModel", "onLocationAutoCompleteClear")
        viewModelScope.launch {
            _address.value = AddressItem()
            clearPredictions()
        }
    }


    private fun clearPredictions() {
        _placePredictions.value = mutableListOf()
    }

    fun onEvent(event: RegisterVehicleUIEvent) {
        when (event) {

            is RegisterVehicleUIEvent.FetchUserId -> {
                getUserId()
            }

            is RegisterVehicleUIEvent.SetBrand -> {
                _uiState.update {
                    it.copy(
                        brand = event.brand
                    )
                }
            }

            is RegisterVehicleUIEvent.SetYear -> {
                _uiState.update {
                    it.copy(
                        year = event.year
                    )
                }
            }

            is RegisterVehicleUIEvent.SetModel -> {
                _uiState.update {
                    it.copy(
                        model = event.model
                    )
                }
            }

            is RegisterVehicleUIEvent.SetLicensePlate -> {
                _uiState.update {
                    it.copy(
                        licensePlate = event.licensePlate
                    )
                }
            }

            is RegisterVehicleUIEvent.SetPrice -> {
                _uiState.update {
                    it.copy(
                        price = event.price
                    )
                }
            }

            is RegisterVehicleUIEvent.SetVehicleClass -> {
                _uiState.update {
                    it.copy(
                        vehicleClass = event.vehicleClass
                    )
                }
            }

            is RegisterVehicleUIEvent.EngineTypeBEVButtonClicked -> {
                _uiState.update {
                    it.copy(
                        engineType = EngineType.BEV
                    )
                }
            }

            is RegisterVehicleUIEvent.EngineTypeFCEVButtonClicked -> {
                _uiState.update {
                    it.copy(
                        engineType = EngineType.FCEV
                    )
                }
            }

            is RegisterVehicleUIEvent.EngineTypeICEButtonClicked -> {
                _uiState.update {
                    it.copy(
                        engineType = EngineType.ICE
                    )
                }
            }

            is RegisterVehicleUIEvent.AvailabilityToggleButtonClicked -> {
                _uiState.update {
                    it.copy(
                        availability = !it.availability
                    )
                }
            }

            is RegisterVehicleUIEvent.SetImgLink -> {
                _uiState.update {
                    it.copy(
                        imgLink = event.imgLink
                    )
                }
            }

            is RegisterVehicleUIEvent.ConfirmRegisterVehicleButtonClicked -> {
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

                val newVehicle = CreateVehicleDTO(
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
                            vehicleRepository.addVehicle(newVehicle)

                            withContext(Dispatchers.Main) {
                                _vehicleUpdated.value = true
                                resetRegisterVehicleUiState()
                            }
                        }
                        Log.d("RegisterVehicleViewModel", "Created vehicle successfully")

                    } catch (e: Exception) {
                        _vehicleUpdated.value = false
                        Log.d("RegisterVehicleViewModel", "Error creating vehicle: $e")
                    }
                }
            }

            is RegisterVehicleUIEvent.CancelRegisterVehicleButtonClicked -> {
                resetRegisterVehicleUiState()
            }

            is RegisterVehicleUIEvent.ResetVehicleUpdated -> {
                _vehicleUpdated.value = false
            }

            RegisterVehicleUIEvent.OnAddressAutoCompleteClear -> {
                viewModelScope.launch {
                    onLocationAutoCompleteClear()
                }
            }

            is RegisterVehicleUIEvent.OnAddressChange -> {
                viewModelScope.launch {

                    _address.update {
                        it.copy(
                            streetAddress = event.address
                        )
                    }
                    getPlacePredictions(event.address)
                }
            }

            is RegisterVehicleUIEvent.OnAddressSelected -> {
                viewModelScope.launch {
                    setLocationPrediction(event.selectedPlaceItem)
                }
            }
        }
    }

    private fun resetRegisterVehicleUiState() {
        _uiState.update {
            it.copy(
                brand = "",
                model = "",
                year = 1999,
                vehicleClass = "",
                engineType = EngineType.ICE,
                licensePlate = "",
                imgLink = 0,
                latitude = 0.0F,
                longitude = 0.0F,
                price = 0.00,
                availability = false
            )
        }
        _address.update {
            it.copy(
                streetAddress = ""
            )
        }
    }
}