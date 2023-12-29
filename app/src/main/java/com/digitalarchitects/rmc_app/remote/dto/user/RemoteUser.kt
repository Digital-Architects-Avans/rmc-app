package com.digitalarchitects.rmc_app.remote.dto.user

import com.digitalarchitects.rmc_app.model.UserType
import kotlinx.serialization.Serializable

@Serializable
data class RemoteUser (
    val id: Int,
    val email: String,
    val userType: UserType,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val street: String,
    val buildingNumber: String,
    val zipCode: String,
    val city: String,
    val imageResourceId: Int? = 0,
)