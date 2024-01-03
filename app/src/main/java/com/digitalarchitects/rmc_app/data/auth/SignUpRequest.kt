package com.digitalarchitects.rmc_app.data.auth

import com.digitalarchitects.rmc_app.domain.model.UserType

data class SignUpRequest(
    val email: String,
    val password: String,
    val userType: UserType,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val street: String,
    val buildingNumber: String,
    val zipCode: String,
    val city: String,
)
