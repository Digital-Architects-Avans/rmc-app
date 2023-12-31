package com.digitalarchitects.rmc_app.presentation.screens.editmyaccount

import com.digitalarchitects.rmc_app.domain.model.UserType

data class EditMyAccountUIState (
    val userId : String = "",
    val email: String = "",
    val password: String = "",
    val userType: UserType = UserType.CLIENT,
    val firstName: String = "",
    val lastName: String = "",
    val phone: String = "",
    val street: String = "",
    val buildingNumber: String = "",
    val zipCode: String = "",
    val city: String = "",
    val imageResourceId: Int = 0
)