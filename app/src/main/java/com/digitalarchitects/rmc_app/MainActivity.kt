package com.digitalarchitects.rmc_app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.digitalarchitects.rmc_app.app.RmcApp
import com.digitalarchitects.rmc_app.data.myaccount.MyAccountUIEvent
import com.digitalarchitects.rmc_app.data.myaccount.MyAccountViewModel
import com.digitalarchitects.rmc_app.model.UserType
import com.digitalarchitects.rmc_app.room.UserDao
import com.digitalarchitects.rmc_app.room.UserDatabase
import com.digitalarchitects.rmc_app.room.UserTable
import com.digitalarchitects.rmc_app.ui.theme.RmcAppTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val db by lazy{
        Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,
            "users.db"
        ).build()
    }

    private val viewModel: MyAccountViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MyAccountViewModel(db.dao) as T
            }
        }
    }


    val job = GlobalScope.launch {
        println("Coroutine is running")

        val user = UserTable(
            email = "john.doe@example.com",
            userType = UserType.CLIENT,
            firstName = "John",
            lastName = "Doe",
            phone = "123-456-7890",
            street = "Main Street",
            buildingNumber = "123",
            zipCode = "12345",
            city = "Example City",
            imageResourceId = R.drawable.usericon, // Replace with an actual resource ID
            id = 1 // Assuming you want to set a specific ID
        )

        val a = db.dao.insertUser(user)


        println("Coroutine completed")
    }
    val b = job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            false
        }

        setContent {
            RmcAppTheme {
                RmcApp(viewModel = viewModel)
            }
        }
    }
}

