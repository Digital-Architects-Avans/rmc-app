package com.digitalarchitects.rmc_app.data.remote.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class SignupDTO(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val street: String,
    val buildingNumber: String,
    val zipCode: String,
    val city: String,
    val profileImageSrc: String
)