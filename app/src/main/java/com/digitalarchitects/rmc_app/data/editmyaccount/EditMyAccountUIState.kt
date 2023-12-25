package com.digitalarchitects.rmc_app.data.editmyaccount

import com.digitalarchitects.rmc_app.model.UserType

data class EditMyAccountUIState (
    val email: String = "",
    val userType: UserType = UserType.CLIENT,
    val firstName: String = "",
    val lastName: String = "",
    val phone: String = "",
    val street: String = "",
    val buildingNumber: String = "",
    val zipCode: String = "",
    val city: String = "",
    val password: String = "" ,
    val imageResourceId: Int = 0,
    val id: Int = 0,
)