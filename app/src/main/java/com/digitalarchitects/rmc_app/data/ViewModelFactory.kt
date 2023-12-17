package com.digitalarchitects.rmc_app.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.digitalarchitects.rmc_app.data.editmyaccount.EditMyAccountViewModel
import com.digitalarchitects.rmc_app.data.login.LoginViewModel
import com.digitalarchitects.rmc_app.data.myaccount.MyAccountViewModel
import com.digitalarchitects.rmc_app.data.register.RegisterViewModel
import com.digitalarchitects.rmc_app.data.search.SearchViewModel
import com.digitalarchitects.rmc_app.data.termsandconditions.TermsAndConditionsViewModel
import com.digitalarchitects.rmc_app.data.welcome.WelcomeViewModel
import com.digitalarchitects.rmc_app.room.RmcRoomDatabase

class ViewModelFactory(
    private val db: RmcRoomDatabase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)) {
            return WelcomeViewModel() as T
        }
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel() as T
        }
        if (modelClass.isAssignableFrom(TermsAndConditionsViewModel::class.java)) {
            return TermsAndConditionsViewModel() as T
        }
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel() as T
        }
        if (modelClass.isAssignableFrom(RentACarViewModel::class.java)) {
            return RentACarViewModel() as T
        }
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel() as T
        }
        if (modelClass.isAssignableFrom(MyRentalsViewModel::class.java)) {
            return MyRentalsViewModel() as T
        }
        if (modelClass.isAssignableFrom(RentMyCarViewModel::class.java)) {
            return RentMyCarViewModel() as T
        }
        if (modelClass.isAssignableFrom(MyVehiclesViewModel::class.java)) {
            return MyVehiclesViewModel() as T
        }
        if (modelClass.isAssignableFrom(RegisterVehicleViewModel::class.java)) {
            return RegisterVehicleViewModel() as T
        }
        if (modelClass.isAssignableFrom(MyAccountViewModel::class.java)) {
            return MyAccountViewModel(db.userDao) as T
        }
        if (modelClass.isAssignableFrom(EditMyAccountViewModel::class.java)) {
            return EditMyAccountViewModel(db.userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}