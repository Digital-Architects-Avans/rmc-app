package com.digitalarchitects.rmc_app.remote.dto.user

import com.digitalarchitects.rmc_app.model.UserType
import kotlinx.serialization.Serializable

@Serializable
data class SignupDTO (
    val email: String,
    val userType: UserType,
    val password: String
)