package com.digitalarchitects.rmc_app.presentation.screens.myaccount

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalarchitects.rmc_app.data.di.IoDispatcher
import com.digitalarchitects.rmc_app.domain.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MyAccountViewModel @Inject constructor(
    private val userRepository: UserRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(MyAccountUIState())
    val uiState: StateFlow<MyAccountUIState> = _state.asStateFlow()

    init {
        fetchUserDetails()
    }

    private fun fetchUserDetails() {
        viewModelScope.launch(dispatcher) {
            try {
                val userId = userRepository.getCurrentUserIdFromDataStore()
                val currentUser = userRepository.getUserById(userId!!)
                withContext(Dispatchers.Main) {
                    _state.value.currentUser = currentUser
                }
            } catch (e: Exception) {
                Log.d("MyAccountViewModel", "error: $e")
            }
        }
    }

    fun onEvent(event: MyAccountUIEvent) {
        when (event) {
            is MyAccountUIEvent.FetchUser -> {
                fetchUserDetails()
            }

            is MyAccountUIEvent.OnLogoutButtonClicked -> {
                // TODO: Implement LOG OUT FUNCTION
            }
        }
    }
}
