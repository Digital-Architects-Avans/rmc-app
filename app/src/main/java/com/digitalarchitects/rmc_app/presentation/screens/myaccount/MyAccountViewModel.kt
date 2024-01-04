package com.digitalarchitects.rmc_app.presentation.screens.myaccount

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalarchitects.rmc_app.data.di.IoDispatcher
import com.digitalarchitects.rmc_app.domain.model.User
import com.digitalarchitects.rmc_app.domain.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MyAccountViewModel @Inject constructor(
    private val userRepository: UserRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(MyAccountUIState())
    private val _uiState = _state
    val uiState: StateFlow<MyAccountUIState> = _uiState.asStateFlow()

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch(dispatcher) {

            val result: Result<List<User>> = runCatching {
                userRepository.getAllUsers()
            }

            result.onSuccess { listResult ->
                uiState.value.users = listResult
            }.onFailure { e ->
                e.printStackTrace()
            }
        }
    }

    fun onEvent(event: MyAccountUIEvent) {
        when (event) {
            is MyAccountUIEvent.ShowUser -> {
                try {
                    runBlocking {
                        withContext(Dispatchers.IO) {
                            val userId = userRepository.getCurrentUserIdFromDataStore()
                            val currentUser = uiState.value.users.find { it.userId == userId }
                            _state.value.currentUser = currentUser
                        }
                    }
                } catch (e: Exception) {
                    Log.d("MyAccountViewModel", "error: $e")
                }
            }

            is MyAccountUIEvent.UpsertUser -> {
                TODO()
            }

            is MyAccountUIEvent.InsertUser -> {
                try {
                    runBlocking {
                        val firstName = withContext(Dispatchers.IO) {
                            // TODO("Implement UI logic to add user database")
                            // userRepository.addUser(localUser.toUser())
                        }
                        //_state.value = _state.value.copy(firstName = "$firstName")
                    }
                } catch (e: Exception) {
//                    _state.value = _state.value.copy(firstName = "Error")
                }
            }


            is MyAccountUIEvent.OnLogoutButtonClicked -> {
                // TODO: LOG OUT FUNCTION

            }
        }
    }
}