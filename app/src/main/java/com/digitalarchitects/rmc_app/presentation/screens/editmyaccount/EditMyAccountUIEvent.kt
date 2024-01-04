package com.digitalarchitects.rmc_app.presentation.screens.editmyaccount

import com.digitalarchitects.rmc_app.domain.model.UserType

sealed interface EditMyAccountUIEvent {

    object InsertUser: EditMyAccountUIEvent
    object UpsertUser: EditMyAccountUIEvent
    object ShowUser: EditMyAccountUIEvent
    data class SetEmail(val email: String) : EditMyAccountUIEvent
    data class SetUserType(val userType: UserType) : EditMyAccountUIEvent
    data class SetFirstName(val firstName: String): EditMyAccountUIEvent
    data class SetLastName(val lastName: String) : EditMyAccountUIEvent
    data class SetPhone(val phone: String) : EditMyAccountUIEvent
    data class SetPassword(val password: String) : EditMyAccountUIEvent
    data class SetStreet(val street: String) : EditMyAccountUIEvent
    data class SetBuildingNumber(val buildingNumber: String) : EditMyAccountUIEvent
    data class SetZipCode(val zipCode: String) : EditMyAccountUIEvent
    data class SetCity(val city: String) : EditMyAccountUIEvent
    data class SetImageResourceId(val imageResourceId: Int) : EditMyAccountUIEvent
    data class SetId(val id: Int) : EditMyAccountUIEvent
    data class User(
        val email: String,
        val userType: UserType,
        val firstName: String,
        val lastName: String,
        val phone: String,
        val street: String,
        val buildingNumber: String,
        val zipCode: String,
        val city: String,
        val imageResourceId: Int,
        val id: Int
    ): EditMyAccountUIEvent
    object ConfirmEditMyAccountButtonClicked: EditMyAccountUIEvent
    object DeleteMyAccountButtonClicked: EditMyAccountUIEvent


}