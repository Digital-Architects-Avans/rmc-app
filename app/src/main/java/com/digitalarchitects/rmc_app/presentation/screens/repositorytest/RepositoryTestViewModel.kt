package com.digitalarchitects.rmc_app.presentation.screens.repositorytest


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalarchitects.rmc_app.data.di.IoDispatcher
import com.digitalarchitects.rmc_app.domain.repo.RentalRepository
import com.digitalarchitects.rmc_app.domain.repo.UserRepository
import com.digitalarchitects.rmc_app.domain.repo.VehicleRepository
import com.digitalarchitects.rmc_app.domain.model.Rental
import com.digitalarchitects.rmc_app.domain.model.User
import com.digitalarchitects.rmc_app.domain.model.Vehicle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * UI state for the [RepositoryTestViewModel]
 */
sealed class ResultUiState<out T> {
    data class Success<T>(val msg: String, val result: List<T>) : ResultUiState<T>()
    data class Error<T>(val error: String) : ResultUiState<T>()
    object Loading : ResultUiState<Nothing>()
}

@HiltViewModel
class RepositoryTestViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val vehicleRepository: VehicleRepository,
    private val rentalRepository: RentalRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    var resultUiState by mutableStateOf(ResultUiState.Loading as ResultUiState<Any>)
        private set

    fun getUsers() {
        viewModelScope.launch(dispatcher) {
                resultUiState = ResultUiState.Loading

            val result: Result<List<User>> = runCatching {
                userRepository.getAllUsers()
            }
            handleResult(result)
        }
    }

    fun getVehicles() {
        viewModelScope.launch(dispatcher) {
                resultUiState = ResultUiState.Loading

            val result: Result<List<Vehicle>> = runCatching {
                vehicleRepository.getAllVehicles()
            }
            handleResult(result)
        }
    }

    fun getRentals() {
        viewModelScope.launch(dispatcher) {
                resultUiState = ResultUiState.Loading

            val result: Result<List<Rental>> = runCatching {
                rentalRepository.getAllRentals()
            }
            handleResult(result)
        }
    }

    private fun <T : Any> handleResult(result: Result<List<T>>) {
        viewModelScope.launch(dispatcher) {
                resultUiState = ResultUiState.Loading

            result.onSuccess { listResult ->
                    resultUiState = ResultUiState.Success(
                        formatSuccessMessage(listResult),
                        listResult
                    )
            }.onFailure { e ->
                    resultUiState = ResultUiState.Error("Error: ${e.message}")
                    e.printStackTrace()
                }
        }
    }

    private fun <T> formatSuccessMessage(listResult: List<T>): String {
        val listCount = listResult.size
        val typeName = if (listCount > 0) listResult[0]!!::class.simpleName else "Item"
        return "Success: $listCount ${if (listCount == 1) typeName else typeName + "s"} retrieved"
    }
}