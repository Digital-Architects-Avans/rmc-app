package com.digitalarchitects.rmc_app.model

import kotlinx.serialization.Serializable

/**
 * Data class that defines aa User which includes the user's id, email, userType, firstName,
 * lastName, phone, street, buildingNumber, zipCode, and city.
 */
@Serializable
data class User (
    val id: Int,
    val email: String,
    val userType: String,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val street: String,
    val buildingNumber: String,
    val zipCode: String,
    val city: String
)
