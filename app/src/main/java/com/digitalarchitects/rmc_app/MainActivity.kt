package com.digitalarchitects.rmc_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.digitalarchitects.rmc_app.app.RmcApp
import com.digitalarchitects.rmc_app.ui.theme.RmcAppTheme

class MainActivity : ComponentActivity() {

//    private val db by lazy {
//        Room.databaseBuilder(
//            applicationContext,
//            RmcRoomDatabase::class.java,
//            "RmcRoomTest1.db"
//        ).build()
//    }
//
//    private val viewModel1: MyAccountViewModel by viewModels {
//        object : ViewModelProvider.Factory {
//            override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                return MyAccountViewModel(db.userDao) as T
//            }
//        }
//    }
//
//    private val viewModel2: EditMyAccountViewModel by viewModels {
//        object : ViewModelProvider.Factory {
//            override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                return EditMyAccountViewModel(db.userDao) as T
//            }
//        }
//    }
//
//    private val viewModel3: WelcomeViewModel by viewModels {
//        object : ViewModelProvider.Factory {
//            override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                return WelcomeViewModel() as T
//            }
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            false
        }

        setContent {
            RmcAppTheme {
                RmcApp(
//                    viewModel1 = viewModel1,
//                    viewModel2 = viewModel2,
//                    viewModel3 = viewModel3
                                )
            }
        }
    }
}

