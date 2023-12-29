package com.digitalarchitects.rmc_app.data.welcome

import androidx.lifecycle.ViewModel
import com.digitalarchitects.rmc_app.app.RmcScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor() : ViewModel() {

    private val _navigateToScreen = MutableStateFlow<RmcScreen?>(null)
    val navigateToScreen = _navigateToScreen.asStateFlow()

    // TODO MAKE onEvent Logic
    fun onRegisterButtonClicked() {
        _navigateToScreen.value = RmcScreen.Register
    }

    fun onLogInButtonClicked() {
        _navigateToScreen.value = RmcScreen.Login
    }
}