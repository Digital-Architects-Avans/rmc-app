package com.digitalarchitects.rmc_app.data.termsandconditions

import androidx.lifecycle.ViewModel
import com.digitalarchitects.rmc_app.app.RmcScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TermsAndConditionsViewModel: ViewModel() {
    private val _navigateToScreen = MutableStateFlow<RmcScreen?>(null)
    val navigateToScreen = _navigateToScreen.asStateFlow()

    // Brian: I have no clue what this screen is
    fun onEvent(event: TermsAndConditionsUIEvent) {
        when (event) {
            TermsAndConditionsUIEvent.ConfirmTermsAndConditionsButtonClicked -> {
//                TODO Datastore accepted, boilerplate?
                _navigateToScreen.value = RmcScreen.RentACar
            }
            TermsAndConditionsUIEvent.DeclineTermsAndConditionsButtonClicked -> {
//                TODO Datastore declined, boilerplate?
                _navigateToScreen.value = RmcScreen.Welcome
//                TODO MESSAGE cant use features of app blah blah
            }
        }


    }
}
