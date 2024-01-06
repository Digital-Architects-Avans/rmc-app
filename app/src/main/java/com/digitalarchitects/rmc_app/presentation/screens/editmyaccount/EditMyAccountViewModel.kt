package com.digitalarchitects.rmc_app.presentation.screens.editmyaccount

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalarchitects.rmc_app.data.remote.dto.user.UpdateUserDTO
import com.digitalarchitects.rmc_app.domain.model.UserType
import com.digitalarchitects.rmc_app.domain.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class EditMyAccountViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(EditMyAccountUIState())
    val uiState: StateFlow<EditMyAccountUIState> = _state.asStateFlow()

    private val _userUpdated = MutableStateFlow(false)
    val userUpdated: StateFlow<Boolean> = _userUpdated.asStateFlow()

    fun onEvent(event: EditMyAccountUIEvent) {
        when (event) {
            is EditMyAccountUIEvent.FetchUser -> {
                viewModelScope.launch {
                    try {
                        val userId = userRepository.getCurrentUserIdFromDataStore()
                        val getUser = withContext(Dispatchers.IO) {
                            userRepository.getUserById(userId!!)
                        }

                        _state.value = _state.value.copy(
                            userId = getUser.userId,
                            email = getUser.email,
                            firstName = getUser.firstName,
                            lastName = getUser.lastName,
                            phone = getUser.phone,
                            street = getUser.street,
                            buildingNumber = getUser.buildingNumber,
                            zipCode = getUser.zipCode,
                            city = getUser.city
                        )
                    } catch (e: Exception) {
                        // Handle the exception or log the error
                        Log.d("EditMyAccountViewModel", "Error fetching user details: $e")
                    }
                }
            }

            is EditMyAccountUIEvent.SetFirstName -> {
                _state.update {
                    it.copy(
                        firstName = event.firstName
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

            is EditMyAccountUIEvent.SetEmail -> {
                _state.update {
                    it.copy(
                        email = event.email
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

            is EditMyAccountUIEvent.SetBuildingNumber -> {
                _state.update {
                    it.copy(
                        buildingNumber = event.buildingNumber
                    )
                }
            }

            is EditMyAccountUIEvent.SetZipCode -> {
                _state.update {
                    it.copy(
                        zipCode = event.zipCode
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

            is EditMyAccountUIEvent.SetPassword -> {
                _state.update {
                    it.copy(
                        password = event.password
                    )
                }
            }

            is EditMyAccountUIEvent.SetImageResourceId -> TODO()

            is EditMyAccountUIEvent.ConfirmEditMyAccountButtonClicked -> {
                val userId = _state.value.userId
                val email = _state.value.email
                val password = _state.value.password
                val firstName = _state.value.firstName
                val lastName = _state.value.lastName
                val phone = _state.value.phone
                val street = _state.value.street
                val buildingNumber = _state.value.buildingNumber
                val zipCode = _state.value.zipCode
                val city = _state.value.city

                val updatedUser = UpdateUserDTO(
                    userId = userId,
                    email = email,
                    password = password,
                    userType = UserType.CLIENT,
                    firstName = firstName,
                    lastName = lastName,
                    phone = phone,
                    street = street,
                    buildingNumber = buildingNumber,
                    zipCode = zipCode,
                    city = city,
                )

                // Had to use a userUpdated stateFlow to notify EditMyAccountScreen that data is updated
                // And use that listener to navigate back to MyAccountScreen
                // Else the user will see the old first name in MyAccountScreen because
                // the uiState is not updated yet when the user navigates back to MyAccountScreen
                viewModelScope.launch {
                    try {
                        withContext(Dispatchers.IO) {
                            userRepository.updateUser(updatedUser.userId, updatedUser)

                            withContext(Dispatchers.Main) {
                                resetPasswordField()

                                // Notify EditMyAccountScreen that data is updated
                                _userUpdated.value = true
                            }
                        }
                    } catch (e: Exception) {
                        _userUpdated.value = false
                        Log.d("EditMyAccountViewModel", "Error updating user: $e")
                    }
                }
            }
            is EditMyAccountUIEvent.ResetUserUpdated -> {
                _userUpdated.value = false
            }
        }
    }

    private fun resetPasswordField() {
        _state.update {
            it.copy(
                password = ""
            )
        }
    }
}