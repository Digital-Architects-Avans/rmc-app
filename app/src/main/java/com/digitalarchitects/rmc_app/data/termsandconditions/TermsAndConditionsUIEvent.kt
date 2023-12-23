package com.digitalarchitects.rmc_app.data.termsandconditions


sealed interface TermsAndConditionsUIEvent {

    object ConfirmTermsAndConditionsButtonClicked: TermsAndConditionsUIEvent
    object DeclineTermsAndConditionsButtonClicked: TermsAndConditionsUIEvent


}