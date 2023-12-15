package com.digitalarchitects.rmc_app.data.myaccount
import androidx.lifecycle.ViewModel
import com.digitalarchitects.rmc_app.data.editmyaccount.EditMyAccountUIEvent
import com.digitalarchitects.rmc_app.model.UserType
import com.digitalarchitects.rmc_app.room.UserDao
import com.digitalarchitects.rmc_app.room.UserTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MyAccountViewModel(
    private val dao: UserDao
//    private val navController: NavController
): ViewModel() {
    private val _state = MutableStateFlow(MyAccountUIState())
    private val _uiState = _state
    val uiState: StateFlow<MyAccountUIState> = _uiState.asStateFlow()
    fun onEvent(event: MyAccountUIEvent){
        when (event) {
            is MyAccountUIEvent.ShowUser -> {
                try{
                    runBlocking {
                        val firstName = withContext(Dispatchers.IO) {
                            dao.getFirstName()
                        }
                       _state.value =  _state.value.copy(firstName = "$firstName")
                    }
                } catch(e: Exception){
                    _state.value = _state.value.copy(firstName = "Error")
                }
            }
            is MyAccountUIEvent.UpsertUser -> {
//                try{
                val user= UserTable(
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
                        val firstName = withContext(Dispatchers.IO) {
                            dao.upsertUser(user)
                        }
                        _state.value =  _state.value.copy(firstName = "$firstName")
                    }
//                } catch(e: Exception){
//                    _state.value = _state.value.copy(firstName = "Error")
//                }
            }
            MyAccountUIEvent.onEditMyAccountButtonClicked -> {
                /*navController.navigate(RmcScreen.EditAccount.name)*/
            }
            MyAccountUIEvent.onLogoutButtonClicked -> {
                // TODO: LOG OUT FUNCTION
//                navController.navigate(RmcScreen.Login.name)
            }
            MyAccountUIEvent.onMyRentalsButtonClicked -> {
                /*navController.navigate(RmcScreen.MyRentals.name)*/
            }
            MyAccountUIEvent.onMyVehiclesButtonClicked -> {
//                navController.navigate(RmcScreen.MyVehicles.name)
            }
            MyAccountUIEvent.onRentOutMyCarButtonClicked -> {
//                navController.navigate(RmcScreen.RentMyCar.name)
            }
        }
    }
}