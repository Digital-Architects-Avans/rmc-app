package com.digitalarchitects.rmc_app.data.myaccount
import androidx.lifecycle.ViewModel
import com.digitalarchitects.rmc_app.room.UserDao
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