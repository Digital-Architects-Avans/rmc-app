package com.digitalarchitects.rmc_app.data.editmyaccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalarchitects.rmc_app.app.RmcScreen
import com.digitalarchitects.rmc_app.model.UserType
import com.digitalarchitects.rmc_app.room.UserDao
import com.digitalarchitects.rmc_app.room.UserTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class EditMyAccountViewModel(
    private val userDao: UserDao,
) : ViewModel() {
    private val _navigateToScreen = MutableStateFlow<RmcScreen?>(null)
    val navigateToScreen = _navigateToScreen.asStateFlow()
    private val _state = MutableStateFlow(EditMyAccountUIState())
    private val _uiState = _state
    val uiState: StateFlow<EditMyAccountUIState> get() = _uiState.asStateFlow()

    fun onEvent(event: EditMyAccountUIEvent) {
        when (event) {
            is EditMyAccountUIEvent.ShowUser -> {
                try {
                    runBlocking {
                        val getUser = withContext(Dispatchers.IO) {
                            userDao.getUserById()
                        }
                        val email = getUser.email
                        val firstName = getUser.firstName
                        val lastName = getUser.lastName
                        val phone = getUser.phone
                        val street = getUser.street
                        val buildingNumber = getUser.buildingNumber
                        val zipCode = getUser.zipCode
                        val city = getUser.city

                        _state.value = _state.value.copy(
                            email = email,
                            firstName = firstName,
                            lastName = lastName,
                            phone = phone,
                            street = street,
                            buildingNumber = buildingNumber,
                            zipCode = zipCode,
                            city = city
                        )
                    }

                } catch (e: Exception) {
//                    _state.value = _state.value.copy(firstName = "Error")
                }
            }

            is EditMyAccountUIEvent.InsertUser -> {
                try {
                    val user = UserTable(
                        email = "john.doe@example.com",
                        userType = UserType.CLIENT,
                        firstName = "John",
                        lastName = "Doe",
                        phone = "+1234567890",
                        street = "Main St",
                        buildingNumber = "123",
                        zipCode = "12345",
                        city = "Cityville",
                        id = 1,
                        imageResourceId = 123
                    )

                    runBlocking {
                        withContext(Dispatchers.IO) {
                            userDao.insertUser(user)
                        }

                        _state.value = _state.value.copy(
                            email = user.email,
                            firstName = user.firstName,
                            lastName = user.lastName,
                            phone = user.phone,
                            street = user.street,
                            buildingNumber = user.buildingNumber,
                            zipCode = user.zipCode,
                            city = user.city
                        )
                    }
                } catch (e: Exception) {
                    // Handle the exception or log an error
                    // TODO ERROR MESSAGE
                    // _state.value = _state.value.copy(firstName = "Error")
                }
            }

            is EditMyAccountUIEvent.SetBuildingNumber -> {
                _state.update {
                    it.copy(
                        buildingNumber = event.buildingNumber
                    )
                }
            }

            is EditMyAccountUIEvent.SetCity -> {
                _state.update {
                    it.copy(
                        city = event.city
                    )
                }
            }

            is EditMyAccountUIEvent.SetEmail -> {
                _state.update {
                    it.copy(
                        email = event.email
                    )
                }
            }

            is EditMyAccountUIEvent.SetFirstName -> {
                _state.update {
                    it.copy(
                        firstName = event.firstName
                    )
                }
            }

            is EditMyAccountUIEvent.SetId -> TODO()
            is EditMyAccountUIEvent.SetImageResourceId -> TODO()
            is EditMyAccountUIEvent.SetPassword -> {
                _state.update {
                    it.copy(
                        lastName = event.password
                    )
                }
            }

            is EditMyAccountUIEvent.SetLastName -> {
                _state.update {
                    it.copy(
                        lastName = event.lastName
                    )
                }
            }

            is EditMyAccountUIEvent.SetPhone -> {
                _state.update {
                    it.copy(
                        phone = event.phone
                    )
                }
            }

            is EditMyAccountUIEvent.SetStreet -> {
                _state.update {
                    it.copy(
                        street = event.street
                    )
                }
            }

            is EditMyAccountUIEvent.SetUserType -> TODO()
            is EditMyAccountUIEvent.SetZipCode -> {
                _state.update {
                    it.copy(
                        zipCode = event.zipCode
                    )
                }
            }

            is EditMyAccountUIEvent.User -> TODO()

            EditMyAccountUIEvent.ConfirmEditMyAccountButtonClicked -> {
                val email = _uiState.value.email
//                val userType = state.value.userType
                val firstName = _uiState.value.firstName
                val lastName = _uiState.value.lastName
                val phone = _uiState.value.phone
                val street = _uiState.value.street
                val buildingNumber = _uiState.value.buildingNumber
                val zipCode = _uiState.value.zipCode
                val city = _uiState.value.city
//                val imageResourceId = state.value.imageResourceId
//                val id = state.value.id
                // TODO ADD MORE PROPERTIES TO IF

                val updatedUser = UserTable(
                    email = email,
                    firstName = firstName,
                    lastName = lastName,
                    phone = phone,
                    street = street,
                    buildingNumber = buildingNumber,
                    zipCode = zipCode,
                    city = city,
                    id = 1
                )
                runBlocking {
                    withContext(Dispatchers.IO) {
                        userDao.upsertUser(user = updatedUser)
                    }
                }
                _navigateToScreen.value = RmcScreen.MyAccount
            }

            EditMyAccountUIEvent.CancelEditMyAccountButtonClicked -> {
                _navigateToScreen.value = RmcScreen.MyAccount
            }

            EditMyAccountUIEvent.DeleteMyAccountButtonClicked -> {
                viewModelScope.launch {
//                  TODO  dao.deleteUser()
                }
            }

            EditMyAccountUIEvent.UpsertUser -> TODO()

            EditMyAccountUIEvent.NavigateUpButtonClicked -> {
                _navigateToScreen.value = RmcScreen.RentACar
            }
        }
    }
}