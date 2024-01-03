package com.digitalarchitects.rmc_app.presentation.screens.termsandconditions

import androidx.lifecycle.ViewModel
import com.digitalarchitects.rmc_app.presentation.RmcScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TermsAndConditionsViewModel @Inject constructor(
) : ViewModel() {
    private val _navigateToScreen = MutableStateFlow<RmcScreen?>(null)
    val navigateToScreen = _navigateToScreen.asStateFlow()

    // Brian: I have no clue what this screen is
    fun onEvent(event: TermsAndConditionsUIEvent) {
        when (event) {
            is TermsAndConditionsUIEvent.ConfirmTermsAndConditionsButtonClicked -> {
//                TODO Datastore accepted, boilerplate?
                _navigateToScreen.value = RmcScreen.RentACar
            }
            is TermsAndConditionsUIEvent.DeclineTermsAndConditionsButtonClicked -> {
//                TODO Datastore declined, boilerplate?
                _navigateToScreen.value = RmcScreen.Welcome
//                TODO MESSAGE cant use features of app blah blah
            }
        }


    }
}
