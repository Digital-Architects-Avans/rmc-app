package com.digitalarchitects.rmc_app.data.remote.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class SigninDTO (
    val email: String,
    val password: String
)