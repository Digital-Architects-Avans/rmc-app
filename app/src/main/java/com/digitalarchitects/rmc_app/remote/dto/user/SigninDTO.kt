package com.digitalarchitects.rmc_app.remote.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class SigninDTO (
    val email: String,
    val password: String
)