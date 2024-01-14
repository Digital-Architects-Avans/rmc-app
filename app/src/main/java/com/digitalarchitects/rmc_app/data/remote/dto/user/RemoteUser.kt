package com.digitalarchitects.rmc_app.data.remote.dto.user

import com.digitalarchitects.rmc_app.domain.model.UserType
import kotlinx.serialization.Serializable

@Serializable
data class RemoteUser (
    val _id: String,
    val userId: String,
    val email: String,
    val password: String,
    val salt: String,
    val userType: UserType,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val street: String,
    val buildingNumber: String,
    val zipCode: String,
    val city: String,
    val profileImageSrc: String
)