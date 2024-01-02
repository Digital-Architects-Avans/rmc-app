package com.digitalarchitects.rmc_app.model

import kotlinx.serialization.Serializable

enum class UserType {
    STAFF, CLIENT, OTHER
}

@Serializable
data class User (
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
    val city: String
)