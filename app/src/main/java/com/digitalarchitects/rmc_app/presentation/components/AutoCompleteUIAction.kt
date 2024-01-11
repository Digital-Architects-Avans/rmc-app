package com.digitalarchitects.rmc_app.presentation.components

sealed class AutoCompleteUIAction {
    object OnDoneClick : AutoCompleteUIAction()
    object OnClearClick : AutoCompleteUIAction()

    data class OnQueryChange(val query: String) : AutoCompleteUIAction()
    data class OnItemSelected<T>(val selectedItem: T) : AutoCompleteUIAction()
}