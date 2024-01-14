package com.digitalarchitects.rmc_app.presentation.screens.myvehicles

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalarchitects.rmc_app.data.di.IoDispatcher
import com.digitalarchitects.rmc_app.domain.model.RentalStatus
import com.digitalarchitects.rmc_app.domain.model.Vehicle
import com.digitalarchitects.rmc_app.domain.repo.RentalRepository
import com.digitalarchitects.rmc_app.domain.repo.UserRepository
import com.digitalarchitects.rmc_app.domain.repo.VehicleRepository
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
class MyVehiclesViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository,
    private val rentalRepository: RentalRepository,
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

                // Set the loading state to false to allow the LazyColumn to load the list
                _uiState.value = _uiState.value.copy(isLoading = false)

            }.onFailure { e ->
                e.printStackTrace()
            }
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

                // Get vehicle stats based on all rentals associated with the vehicle
                if (vehicleId != null) {
                    getVehicleStats(vehicleId)
                }

                // Update the UI state with the selected vehicle
                if (selectedVehicle != null) {
                    _uiState.update {
                        it.copy(
                            selectedVehicle = selectedVehicle,
                            isAvailable = selectedVehicle.availability
                        )
                    }
                }
            }

            is MyVehiclesUIEvent.CancelShowVehicleDetails -> {
                _uiState.update {
                    it.copy(selectedVehicle = null)
                }
            }

            is MyVehiclesUIEvent.ChangeAvailability -> {
                val vehicleId: String = event.vehicleId

                viewModelScope.launch(dispatcher) {
                    try {
                        _uiState.value.isAvailable = !_uiState.value.isAvailable
                        vehicleRepository.setVehicleAvailability(
                            vehicleId = vehicleId,
                            availability = _uiState.value.isAvailable
                        )
                        getVehiclesOfUser()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
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

    // Get all rentals associated with the vehicle and calculate stats
    private fun getVehicleStats(vehicleId: String) {
        viewModelScope.launch(dispatcher) {
            try {

                // Get rental for vehicle
                val vehicleRentals = runCatching {
                    rentalRepository.getRentalsForVehicle(vehicleId)!!
                        .filter { it.status != RentalStatus.PENDING }
                }

                withContext(Dispatchers.Main) {
                    vehicleRentals.onSuccess { vehicleRentals ->
                        // Total rentals
                        _uiState.value.stats_rentals =
                            vehicleRentals.count()
                        Log.d(
                            "MyVehicleViewModel",
                            "Total rentals for vehicle: ${_uiState.value.stats_rentals}"
                        )
                        // Total distance
                        _uiState.value.stats_distance =
                            vehicleRentals.sumOf { it.distanceTravelled }.toInt()
                        Log.d(
                            "MyVehicleViewModel",
                            "Total distance for vehicle: ${_uiState.value.stats_distance}"
                        )
                        // Total rating
                        _uiState.value.stats_rating =
                            vehicleRentals.sumOf { it.score }
                        Log.d(
                            "MyVehicleViewModel",
                            "Average score for vehicle: ${_uiState.value.stats_rating}"
                        )
                        // Total earnings
                        _uiState.value.stats_earnings =
                            vehicleRentals.sumOf { it.price }.toInt()
                        Log.d(
                            "MyVehicleViewModel",
                            "Total earnings for vehicle: ${_uiState.value.stats_earnings}"
                        )
                    }
                }.onFailure { e ->
                    e.printStackTrace()
                }
            } catch (e: Exception) {
                // Handle any unexpected exceptions
                e.printStackTrace()
            }
        }
    }
}