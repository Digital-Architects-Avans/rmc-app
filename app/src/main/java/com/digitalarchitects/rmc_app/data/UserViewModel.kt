package com.digitalarchitects.rmc_app.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.digitalarchitects.rmc_app.RmcApplication
import com.digitalarchitects.rmc_app.model.User
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the Home screen
 */
sealed interface UserUiState {
    data class Success(val msg: String, val users: String) : UserUiState
    data class Error(val error: String) : UserUiState
    object Loading : UserUiState
}

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var userUiState: UserUiState by mutableStateOf(UserUiState.Loading)
        private set

    /**
     * Call getUsers() on init so we can display status immediately.
     */
    init {
        getUsers()
    }

    /**
     * Gets user information from the Rmc API Retrofit service and updates the
     * [User] [List] [MutableList].
     */
    fun getUsers() {
        viewModelScope.launch {
            userUiState = UserUiState.Loading
            userUiState = try {
                // Get the list of users from the Rmc API Retrofit service
                val listResult = userRepository.getUsers()

                // Convert each User to its JSON representation with pretty print
                val formattedJsonList = listResult.map { user ->
                    Json { prettyPrint = true }.encodeToString(user)
                }

                UserUiState.Success(
                    "Success: ${listResult.size} user retrieved",
                    formattedJsonList.joinToString("\n") // Combine the JSON strings with line breaks
                )
            } catch (e: IOException) {
                UserUiState.Error(e.message ?: "Error: IOException")
            } catch (e: HttpException) {
                UserUiState.Error(e.message ?: "Error: HttpException")
            }
        }
    }

    /**
     * Factory for [UserViewModel] that takes [UserRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as RmcApplication)
                val userRepository = application.container.userRepository
                UserViewModel(userRepository = userRepository)
            }
        }
    }
}