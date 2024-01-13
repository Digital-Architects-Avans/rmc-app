package com.digitalarchitects.rmc_app.presentation.screens.rentoutmycar

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalarchitects.rmc_app.data.di.IoDispatcher
import com.digitalarchitects.rmc_app.domain.model.Rental
import com.digitalarchitects.rmc_app.domain.model.RentalStatus
import com.digitalarchitects.rmc_app.domain.model.User
import com.digitalarchitects.rmc_app.domain.model.Vehicle
import com.digitalarchitects.rmc_app.domain.repo.RentalRepository
import com.digitalarchitects.rmc_app.domain.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject

@HiltViewModel
class RentOutMyCarViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val rentalRepository: RentalRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(RentOutMyCarUIState())
    val uiState: StateFlow<RentOutMyCarUIState> get() = _uiState.asStateFlow()

    init {
        getRentalsForUserVehicles()
    }

    // Function that returns a Triple containing the vehicle, user and rental for each
    // rental that is associated with the user
    private fun getRentalsForUserVehicles() {
        viewModelScope.launch(dispatcher) {

            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                val userId = userRepository.getCurrentUserIdFromDataStore()
                val today = Clock.System.now().toLocalDateTime(
                    TimeZone.currentSystemDefault()
                ).date

                if (userId != null) {

                    // Fetch all relevant data in a single call
                    val triplesResult: Result<List<Triple<Rental, Vehicle, User>>> = runCatching {
                        rentalRepository.getListOfRentalDetailsForOwner(userId)
                    }

                    withContext(Dispatchers.Main) {
                        triplesResult.onSuccess { triples ->
                            _uiState.value.listOfRentalsForUser = triples

                            _uiState.value.pendingRentalsList =
                                triples.filter { it.first.date >= today && it.first.status == RentalStatus.PENDING }
                            _uiState.value.openRentalsList =
                                triples.filter { it.first.date >= today && it.first.status == RentalStatus.APPROVED }
                            _uiState.value.historyRentalsList = triples.filter {
                                it.first.date < today || it.first.status == RentalStatus.DENIED || it.first.status == RentalStatus.CANCELLED
                            }
                        }
                    }.onFailure { e ->
                        e.printStackTrace()
                    }
                    _uiState.value = _uiState.value.copy(isLoading = false)
                } else {
                    // Log an error message if userId is null
                    Log.e("RentMyCarViewModel", "UserId is null")
                }
            } catch (e: Exception) {
                // Handle any unexpected exceptions
                e.printStackTrace()
            }
        }
    }

    fun onEvent(event: RentOutMyCarUIEvent) {
        when (event) {
            is RentOutMyCarUIEvent.FetchRentals -> {
                getRentalsForUserVehicles()
            }

            is RentOutMyCarUIEvent.ShowRentalDetails -> {

                when (event.tab) {
                    RentalTab.PENDING -> {
                        _uiState.update {
                            it.copy(selectedRentalItem = _uiState.value.pendingRentalsList[event.index])
                        }
                    }

                    RentalTab.OPEN -> {
                        _uiState.update {
                            it.copy(selectedRentalItem = _uiState.value.openRentalsList[event.index])
                        }
                    }

                    RentalTab.HISTORY -> {
                        _uiState.update {
                            it.copy(selectedRentalItem = _uiState.value.historyRentalsList[event.index])
                        }
                    }
                }
            }

            is RentOutMyCarUIEvent.AcceptRental -> {
                // Access the rentalId property directly from the event
                val rentalId: String = event.rentalId

                viewModelScope.launch(dispatcher) {
                    try {
                        rentalRepository.setRentalStatus(rentalId, RentalStatus.APPROVED)
                        Log.d("RentoutMyCarViewModel", "Accepted rental with id: $rentalId")
                    } catch (e: Exception) {
                        Log.e("RentoutMyCarViewModel", "Error: Could not set rental status", e)
                        e.printStackTrace()
                    }
                        getRentalsForUserVehicles()
                }
            }

            is RentOutMyCarUIEvent.RejectRental -> {
                // Access the rentalId property directly from the event
                val rentalId: String = event.rentalId

                viewModelScope.launch(dispatcher) {
                    try {
                        rentalRepository.setRentalStatus(rentalId, RentalStatus.DENIED)
                        Log.d("RentoutMyCarViewModel", "Rejected rental with id: $rentalId")
                    } catch (e: Exception) {
                        Log.e("RentoutMyCarViewModel", "Error: Could not set rental status", e)
                        e.printStackTrace()
                    }

                    withContext(Dispatchers.Main) {
                        getRentalsForUserVehicles()
                    }
                }
            }

            is RentOutMyCarUIEvent.CancelShowRentalDetails -> {
                _uiState.update {
                    it.copy(selectedRentalItem = null)
                }
            }

            is RentOutMyCarUIEvent.SelectTab -> {
                _uiState.update {
                    it.copy(selectedTab = event.tab)
                }
            }
        }

    }
}