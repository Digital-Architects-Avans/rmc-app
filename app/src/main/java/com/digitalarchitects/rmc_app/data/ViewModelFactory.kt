package com.digitalarchitects.rmc_app.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.digitalarchitects.rmc_app.data.myrentals.MyRentalsViewModel
import com.digitalarchitects.rmc_app.data.myvehicles.MyVehiclesViewModel
import com.digitalarchitects.rmc_app.data.rentoutmycar.RentOutMyCarViewModel
import com.digitalarchitects.rmc_app.data.editmyaccount.EditMyAccountViewModel
import com.digitalarchitects.rmc_app.data.editmyvehicle.EditMyVehicleViewModel
import com.digitalarchitects.rmc_app.data.login.LoginViewModel
import com.digitalarchitects.rmc_app.data.myaccount.MyAccountViewModel
import com.digitalarchitects.rmc_app.data.register.RegisterViewModel
import com.digitalarchitects.rmc_app.data.registervehicle.RegisterVehicleViewModel
import com.digitalarchitects.rmc_app.data.rentacar.RentACarViewModel
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
        if (modelClass.isAssignableFrom(EditMyVehicleViewModel::class.java)) {
            return EditMyVehicleViewModel(vehicleDao = db.vehicleDao) as T
        }
        if (modelClass.isAssignableFrom(TermsAndConditionsViewModel::class.java)) {
            return TermsAndConditionsViewModel() as T
        }
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel() as T
        }
        if (modelClass.isAssignableFrom(RentACarViewModel::class.java)) {
            return RentACarViewModel(vehicleDao = db.vehicleDao) as T
        }
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel() as T
        }
        if (modelClass.isAssignableFrom(MyRentalsViewModel::class.java)) {
            return MyRentalsViewModel(rentalDao = db.rentalDao) as T
        }
        if (modelClass.isAssignableFrom(RentOutMyCarViewModel::class.java)) {
            return RentOutMyCarViewModel(vehicleDao = db.vehicleDao, rentalDao = db.rentalDao) as T
        }
        if (modelClass.isAssignableFrom(MyVehiclesViewModel::class.java)) {
            return MyVehiclesViewModel(db.vehicleDao) as T
        }
        if (modelClass.isAssignableFrom(RegisterVehicleViewModel::class.java)) {
            return RegisterVehicleViewModel(db.vehicleDao) as T
        }
        if (modelClass.isAssignableFrom(MyAccountViewModel::class.java)) {
            return MyAccountViewModel(userDao = db.userDao) as T
        }
        if (modelClass.isAssignableFrom(EditMyAccountViewModel::class.java)) {
            return EditMyAccountViewModel(userDao = db.userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}