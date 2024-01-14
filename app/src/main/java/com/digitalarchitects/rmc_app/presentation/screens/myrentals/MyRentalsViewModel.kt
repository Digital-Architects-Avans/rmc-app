package com.digitalarchitects.rmc_app.presentation.screens.myrentals

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
class MyRentalsViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val rentalRepository: RentalRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(MyRentalsUIState())
    val uiState: StateFlow<MyRentalsUIState> get() = _uiState.asStateFlow()

    init {
        getMyRentals()
    }

    // Function that returns a Triple containing the vehicle, user and rental for each
    // rental that is associated with the user
    private fun getMyRentals() {
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
                        rentalRepository.getListOfRentalDetailsForRenter(userId)
                    }

                    withContext(Dispatchers.Main) {
                        triplesResult.onSuccess { triples ->
                            _uiState.value.myRentalsList = triples

                            _uiState.value.myOpenRentalsList =
                                triples.filter { it.first.date >= today && it.first.status == RentalStatus.APPROVED || it.first.status == RentalStatus.PENDING }
                            Log.d(
                                "MyRentalsViewModel",
                                "Open rentals: ${_uiState.value.myOpenRentalsList}"
                            )
                            _uiState.value.myHistoryRentalList = triples.filter {
                                it.first.date < today || it.first.status == RentalStatus.DENIED || it.first.status == RentalStatus.CANCELLED
                            }
                        }
                    }.onFailure { e ->
                        e.printStackTrace()
                    }
                    _uiState.value = _uiState.value.copy(isLoading = false)
                } else {
                    // Log an error message if userId is null
                    Log.e("MyRentalsViewModel", "UserId is null")
                }
            } catch (e: Exception) {
                // Handle any unexpected exceptions
                e.printStackTrace()
            }
        }
    }

    fun onEvent(event: MyRentalsUIEvent) {
        when (event) {
            is MyRentalsUIEvent.FetchMyRentals -> {
                getMyRentals()
            }

            is MyRentalsUIEvent.ShowRentalDetails -> {

                when (event.tab) {
                    MyRentalTab.OPEN -> {
                        _uiState.update {
                            it.copy(selectedRentalItem = _uiState.value.myOpenRentalsList[event.index])
                        }
                    }

                    MyRentalTab.HISTORY -> {
                        _uiState.update {
                            it.copy(selectedRentalItem = _uiState.value.myHistoryRentalList[event.index])
                        }
                    }
                }
            }

            is MyRentalsUIEvent.SelectTab -> {
                _uiState.update {
                    it.copy(selectedTab = event.tab)
                }
            }

            is MyRentalsUIEvent.RouteToRental -> {
                _uiState.update {
                    it.copy(routeToRental = event.location)
                }
            }

            is MyRentalsUIEvent.CancelRental -> {
                // Access the rentalId property directly from the event
                val rentalId: String = event.rentalId

                viewModelScope.launch(dispatcher) {
                    try {
                        rentalRepository.setRentalStatus(rentalId, RentalStatus.CANCELLED)
                        Log.d("MyRentalsViewModel", "Cancelled rental with id: $rentalId")
                    } catch (e: Exception) {
                        Log.e("MyRentalsViewModel", "Error: Could not set rental status", e)
                        e.printStackTrace()
                    }

                    withContext(Dispatchers.Main) {
                        getMyRentals()
                    }
                }
            }

            is MyRentalsUIEvent.CancelShowRentalDetails -> {
                _uiState.update {
                    it.copy(selectedRentalItem = null)
                }
            }
        }
    }
}