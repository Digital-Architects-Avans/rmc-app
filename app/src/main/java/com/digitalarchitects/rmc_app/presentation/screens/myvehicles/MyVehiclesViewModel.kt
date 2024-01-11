package com.digitalarchitects.rmc_app.presentation.screens.myvehicles

import android.content.Context
import android.location.Geocoder
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalarchitects.rmc_app.data.di.IoDispatcher
import com.digitalarchitects.rmc_app.domain.model.Vehicle
import com.digitalarchitects.rmc_app.domain.repo.UserRepository
import com.digitalarchitects.rmc_app.domain.repo.VehicleRepository
import com.digitalarchitects.rmc_app.domain.util.getAddressFromLatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyVehiclesViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val vehicleRepository: VehicleRepository,
    private val userRepository: UserRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(MyVehiclesUIState())
    val uiState: StateFlow<MyVehiclesUIState> = _uiState.asStateFlow()

    init {
        getVehiclesOfUser()
    }

    private fun getVehiclesOfUser() {
        viewModelScope.launch(dispatcher) {

            // Set the loading state to true to prevent the LazyColumn to load the list
            _uiState.value = _uiState.value.copy(isLoading = true)

            val result: Result<List<Vehicle>> = runCatching {
                vehicleRepository.getAllVehicles()
            }
            val userId = userRepository.getCurrentUserIdFromDataStore()

            result.onSuccess { listOfVehicles ->
                _uiState.value.listOfVehicles = listOfVehicles.filter { vehicle ->
                    vehicle.userId == userId
                }

                _uiState.value.listOfLocations = listOfVehicles.map { vehicle ->
                    getAddressFromLatLng(
                        vehicle.latitude.toDouble(),
                        vehicle.longitude.toDouble(),
                        detailed = false
                    )
                }

                _uiState.value.listOfLocationsDetailed = listOfVehicles.map { vehicle ->
                    getAddressFromLatLng(
                        vehicle.latitude.toDouble(),
                        vehicle.longitude.toDouble(),
                        detailed = true
                    )
                }

                // Set the loading state to false to allow the LazyColumn to load the list
                _uiState.value = _uiState.value.copy(isLoading = false)

            }.onFailure { e ->
                e.printStackTrace()
            }
        }
    }

    private suspend fun getAddressFromLatLng(
        latitude: Double,
        longitude: Double,
        detailed: Boolean
    ): String {
        return try {
            val geoCoder = Geocoder(context)
            val address = geoCoder.getAddressFromLatLng(latitude, longitude)

            if (detailed) {
                return address?.getAddressLine(0) ?: ""
            }

            // Exclude country and postalCode from the address when detailed is false
            val addressLine = address?.getAddressLine(0) ?: ""
            val country = address?.countryName.orEmpty()
            val postalCode = address?.postalCode.orEmpty()

            // Remove the country and postalCode from the address
            val formattedAddress = addressLine
                .replace(", $country", "")
                .replace(", $postalCode", "")

            formattedAddress
        } catch (e: Exception) {
            Log.d("MyVehiclesViewModel", "getAddressFromLatLng: ${e.message}")
            e.printStackTrace()
            ""
        }
    }


    fun onEvent(event: MyVehiclesUIEvent) {
        when (event) {
            is MyVehiclesUIEvent.FetchVehicles -> {
                getVehiclesOfUser()
            }

            is MyVehiclesUIEvent.ShowVehicleDetails -> {
                // Access the vehicleId property directly from the event
                val vehicleId: String? = event.vehicleId

                // Find the vehicle with the matching vehicleId
                val selectedVehicle =
                    uiState.value.listOfVehicles.find { it.vehicleId == vehicleId }

                // Update the UI state with the selected vehicle
                _uiState.update {
                    it.copy(selectedVehicle = selectedVehicle)
                }
            }

            is MyVehiclesUIEvent.CancelShowVehicleDetails -> {
                _uiState.update {
                    it.copy(selectedVehicle = null)
                }
            }

            is MyVehiclesUIEvent.DeleteVehicle -> {
                val vehicleId: String = event.vehicleId

                viewModelScope.launch(dispatcher) {
                    try {
                        vehicleRepository.deleteVehicle(vehicleId = vehicleId)
                        getVehiclesOfUser()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

            }
        }
    }
}