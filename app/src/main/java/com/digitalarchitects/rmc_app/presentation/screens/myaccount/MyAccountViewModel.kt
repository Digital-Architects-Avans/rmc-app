package com.digitalarchitects.rmc_app.presentation.screens.myaccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalarchitects.rmc_app.data.di.IoDispatcher
import com.digitalarchitects.rmc_app.data.local.LocalUser
import com.digitalarchitects.rmc_app.domain.model.User
import com.digitalarchitects.rmc_app.domain.model.UserType
import com.digitalarchitects.rmc_app.domain.repo.UserRepository
import com.digitalarchitects.rmc_app.presentation.RmcScreen
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
    private val _navigateToScreen = MutableStateFlow<RmcScreen?>(null)
    val navigateToScreen = _navigateToScreen.asStateFlow()
    private val _state = MutableStateFlow(MyAccountUIState())
    private val _uiState = _state
    val uiState: StateFlow<MyAccountUIState> = _uiState.asStateFlow()


    fun getUsers() {
        viewModelScope.launch(dispatcher) {

            val result: Result<List<User>> = runCatching {
                userRepository.getAllUsers()
            }
        }
    }

    fun onEvent(event: MyAccountUIEvent) {
        when (event) {
            is MyAccountUIEvent.ShowUser -> {
                try {
                    runBlocking {
                        val firstName = withContext(Dispatchers.IO) {
                            userRepository.getFirstName("659579bb24eca74f47b8d194")
                        }
                        _state.value = _state.value.copy(firstName = "$firstName")
                    }
                } catch (e: Exception) {
                    _state.value = _state.value.copy(firstName = "Error")
                }
            }

            is MyAccountUIEvent.InsertUser -> {
                try {
                    val localUser = LocalUser(
                        userId = "1",
                        email = "john.doe@example.com",
                        password = "password",
                        salt = "salt",
                        userType = UserType.CLIENT,
                        firstName = "John",
                        lastName = "Doe",
                        phone = "+1234567890",
                        street = "Main St",
                        buildingNumber = "123",
                        zipCode = "12345",
                        city = "Cityville",
                        imageResourceId = null
                    )
                    runBlocking {
                        val firstName = withContext(Dispatchers.IO) {
                            // TODO("Implement UI logic to add user database")
                            // userRepository.addUser(localUser.toUser())
                        }
                        _state.value = _state.value.copy(firstName = "$firstName")
                    }
                } catch (e: Exception) {
//                    _state.value = _state.value.copy(firstName = "Error")
                }
            }

            is MyAccountUIEvent.onEditMyAccountButtonClicked -> {
                _navigateToScreen.value = RmcScreen.EditMyAccount
            }

            is MyAccountUIEvent.onLogoutButtonClicked -> {
                // TODO: LOG OUT FUNCTION
                _navigateToScreen.value = RmcScreen.Welcome

            }

            is MyAccountUIEvent.onMyRentalsButtonClicked -> {
                _navigateToScreen.value = RmcScreen.MyRentals
            }

            is MyAccountUIEvent.onMyVehiclesButtonClicked -> {
                _navigateToScreen.value = RmcScreen.MyVehicles
            }

            is MyAccountUIEvent.onRentOutMyCarButtonClicked -> {
                _navigateToScreen.value = RmcScreen.RentOutMyCar
            }

            is MyAccountUIEvent.UpsertUser -> TODO()
        }
    }
}