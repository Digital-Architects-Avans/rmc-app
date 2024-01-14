package com.digitalarchitects.rmc_app.presentation.screens.editmyaccount

import android.app.Application
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalarchitects.rmc_app.data.remote.dto.user.UpdateUserDTO
import com.digitalarchitects.rmc_app.domain.model.UserType
import com.digitalarchitects.rmc_app.domain.repo.FileRepository
import com.digitalarchitects.rmc_app.domain.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.FileInputStream
import javax.inject.Inject


@HiltViewModel
class EditMyAccountViewModel @Inject constructor(
    private val application: Application,
    private val userRepository: UserRepository,
    private val fileRepository: FileRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditMyAccountUIState())
    val uiState: StateFlow<EditMyAccountUIState> = _uiState.asStateFlow()

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

                            _uiState.value = _uiState.value.copy(
                                userId = getUser.userId,
                                email = getUser.email,
                                firstName = getUser.firstName,
                                lastName = getUser.lastName,
                                phone = getUser.phone,
                                street = getUser.street,
                                buildingNumber = getUser.buildingNumber,
                                zipCode = getUser.zipCode,
                                city = getUser.city,
                                profileImageSrc = getUser.profileImageSrc
                            )

                    } catch (e: Exception) {
                        // Handle the exception or log the error
                        Log.d("EditMyAccountViewModel", "Error fetching user details: $e")
                    }
                }
            }

            is EditMyAccountUIEvent.SetFirstName -> {
                _uiState.update {
                    it.copy(
                        firstName = event.firstName
                    )
                }
            }

            is EditMyAccountUIEvent.SetLastName -> {
                _uiState.update {
                    it.copy(
                        lastName = event.lastName
                    )
                }
            }

            is EditMyAccountUIEvent.SetEmail -> {
                _uiState.update {
                    it.copy(
                        email = event.email
                    )
                }
            }

            is EditMyAccountUIEvent.SetPhone -> {
                _uiState.update {
                    it.copy(
                        phone = event.phone
                    )
                }
            }

            is EditMyAccountUIEvent.SetStreet -> {
                _uiState.update {
                    it.copy(
                        street = event.street
                    )
                }
            }

            is EditMyAccountUIEvent.SetBuildingNumber -> {
                _uiState.update {
                    it.copy(
                        buildingNumber = event.buildingNumber
                    )
                }
            }

            is EditMyAccountUIEvent.SetZipCode -> {
                _uiState.update {
                    it.copy(
                        zipCode = event.zipCode
                    )
                }
            }

            is EditMyAccountUIEvent.SetCity -> {
                _uiState.update {
                    it.copy(
                        city = event.city
                    )
                }
            }

            is EditMyAccountUIEvent.SetPassword -> {
                _uiState.update {
                    it.copy(
                        password = event.password
                    )
                }
            }

            is EditMyAccountUIEvent.ConfirmEditMyAccountButtonClicked -> {
                val userId = _uiState.value.userId
                val email = _uiState.value.email
                val password = _uiState.value.password
                val firstName = _uiState.value.firstName
                val lastName = _uiState.value.lastName
                val phone = _uiState.value.phone
                val street = _uiState.value.street
                val buildingNumber = _uiState.value.buildingNumber
                val zipCode = _uiState.value.zipCode
                val city = _uiState.value.city
                val profileImageSrc = _uiState.value.profileImageSrc

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
                    profileImageSrc = profileImageSrc ?: ""
                )

                // Had to use a userUpdated stateFlow to notify EditMyAccountScreen that data is updated
                // And use that listener to navigate back to MyAccountScreen
                // Else the user will see the old first name in MyAccountScreen because
                // the uiState is not updated yet when the user navigates back to MyAccountScreen
                viewModelScope.launch {
                    try {
                        withContext(Dispatchers.IO) {
                            userRepository.updateUser(updatedUser.userId, updatedUser)

                            // Upload the selected profile image
                            if (uiState.value.imageUri != Uri.EMPTY) {
                                uploadImage(uiState.value.imageUri)
                            }

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
                _uiState.update {
                    it.copy(
                        imgUpdated = false
                    )
                }
            }

            is EditMyAccountUIEvent.SetImageUri -> {
                _uiState.update {
                    it.copy(
                        imageUri = event.uri,
                        imgUpdated = true
                    )
                }
                Log.d("EditMyAccountViewModel", "Set ImageURI: ${uiState.value.imageUri}")
            }
        }
    }

    private suspend fun uploadImage(uri: Uri) {
        val context: Context = application.applicationContext
        val contentResolver = context.contentResolver
        val fileDescriptor = contentResolver.openFileDescriptor(uri, "r")?.fileDescriptor

        if (fileDescriptor != null) {
            val tempFile = kotlin.io.path.createTempFile().toFile()

            try {
                FileInputStream(fileDescriptor).use { input ->
                    tempFile.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }

                // Now, you can use tempFile for uploading
                val filePath = fileRepository.uploadImage(tempFile)
                Log.d("EditMyAccountViewModel", "Uploaded image profileImageSrc path: $filePath")

                // update the profileImageSrc in the user
                userRepository.setProfileImageSrc(_uiState.value.userId, filePath)

            } catch (e: Exception) {
                // Handle error
                Log.e("EditMyAccountViewModel", "Error uploading image: $e")
            } finally {
                tempFile.delete() // Clean up temporary file
            }
        } else {
            Log.e("EditMyAccountViewModel", "Failed to obtain file descriptor for $uri")
        }
    }

    private fun resetPasswordField() {
        _uiState.update {
            it.copy(
                password = ""
            )
        }
    }
}