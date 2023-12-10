package com.digitalarchitects.rmc_app.data.editmyaccount

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.digitalarchitects.rmc_app.app.RmcScreen
import com.digitalarchitects.rmc_app.room.UserDao
import com.digitalarchitects.rmc_app.room.UserTable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class EditMyAccountViewModel(
    private val dao: UserDao,
    private val navController: NavController
    ): ViewModel() {

    private val _state = MutableStateFlow(EditMyAccountUIState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        EditMyAccountUIState()
    )
    fun onEvent(event: EditMyAccountUIEvent){
        when(event){
            is EditMyAccountUIEvent.SetBuildingNumber -> {
                _state.update { it.copy(
                    buildingNumber = event.buildingNumber
                )}
            }
            is EditMyAccountUIEvent.SetCity -> {
                _state.update { it.copy(
                    city = event.city
                )}
            }
            is EditMyAccountUIEvent.SetEmail -> {
                _state.update { it.copy(
                    email = event.email
                )}
            }
            is EditMyAccountUIEvent.SetFirstName -> {
                _state.update { it.copy(
                    firstName = event.firstName
                )}
            }
            is EditMyAccountUIEvent.SetId -> TODO()
            is EditMyAccountUIEvent.SetImageResourceId -> TODO()
            is EditMyAccountUIEvent.SetLastName -> {
                _state.update { it.copy(
                    lastName = event.lastName
                )}
            }
            is EditMyAccountUIEvent.SetPhone -> {
                _state.update { it.copy(
                    phone = event.phone
                )}
            }
            is EditMyAccountUIEvent.SetStreet -> {
                _state.update { it.copy(
                    street = event.street
                )}
            }
            is EditMyAccountUIEvent.SetUserType -> TODO()
            is EditMyAccountUIEvent.SetZipCode -> {
                _state.update { it.copy(
                    zipCode = event.zipCode
                )}
            }
            is EditMyAccountUIEvent.User -> TODO()
            EditMyAccountUIEvent.ConfirmEditMyAccountButtonClicked -> {
                val email = state.value.email
//                val userType = state.value.userType
                val firstName = state.value.firstName
                val lastName = state.value.lastName
                val phone = state.value.phone
                val street = state.value.street
                val buildingNumber = state.value.buildingNumber
                val zipCode = state.value.zipCode
                val city = state.value.city
//                val imageResourceId = state.value.imageResourceId
//                val id = state.value.id
                // TODO ADD MORE PROPERTIES TO IF
                if(firstName.isBlank() || lastName.isBlank() || phone.isBlank()){
                    return
                }
                val user = UserTable(
                    email = email,
                    firstName = firstName,
                    lastName = lastName,
                    phone = phone,
                    street = street,
                    buildingNumber = buildingNumber,
                    zipCode = zipCode,
                    city = city
                )
                viewModelScope.launch {
                    dao.upsertUser(user = user)
                    navController.navigate(RmcScreen.MyAccount.name)
                }


            }
            EditMyAccountUIEvent.CancelEditMyAccountButtonClicked -> {
                navController.navigate(RmcScreen.MyAccount.name)
            }
            EditMyAccountUIEvent.DeleteMyAccountButtonClicked -> {
                viewModelScope.launch {
//                    dao.deleteUser()
                }
                navController.navigate(RmcScreen.Welcome.name)
            }
        }
    }
}