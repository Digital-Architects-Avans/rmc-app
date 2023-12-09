package com.digitalarchitects.rmc_app.data.myaccount
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.digitalarchitects.rmc_app.app.RmcScreen
import com.digitalarchitects.rmc_app.room.UserDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MyAccountViewModel(
    private val dao: UserDao,
    private val navController: NavController
): ViewModel() {
    private val _state = MutableStateFlow(MyAccountUIState())
    // private val _users = dao.getUsersOrderedByFirstName()
    fun onEvent(event: MyAccountUIEvent){
        when (event) {
            is MyAccountUIEvent.ShowUser -> {
                viewModelScope.launch {
                    dao.getUser()
                }
            }
            MyAccountUIEvent.onEditMyAccountButtonClicked -> {
                /*navController.navigate(RmcScreen.EditAccount.name)*/
            }
            MyAccountUIEvent.onLogoutButtonClicked -> {
                // TODO: LOG OUT FUNCTION
                navController.navigate(RmcScreen.Login.name)
            }
            MyAccountUIEvent.onMyRentalsButtonClicked -> {
                /*navController.navigate(RmcScreen.MyRentals.name)*/
            }
            MyAccountUIEvent.onMyVehiclesButtonClicked -> {
                navController.navigate(RmcScreen.MyVehicles.name)
            }
            MyAccountUIEvent.onRentOutMyCarButtonClicked -> {
                navController.navigate(RmcScreen.RentMyCar.name)
            }
        }
    }
}