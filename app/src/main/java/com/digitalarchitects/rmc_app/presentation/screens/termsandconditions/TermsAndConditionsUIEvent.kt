package com.digitalarchitects.rmc_app.presentation.screens.termsandconditions


sealed interface TermsAndConditionsUIEvent {

    object ConfirmTermsAndConditionsButtonClicked: TermsAndConditionsUIEvent
    object DeclineTermsAndConditionsButtonClicked: TermsAndConditionsUIEvent


}