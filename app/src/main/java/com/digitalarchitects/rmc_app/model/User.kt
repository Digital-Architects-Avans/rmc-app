package com.digitalarchitects.rmc_app.model

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val telephone: String,
    val password: String,
    val address: String,
    val postalCode: String,
    val buildingNumber: String,
    val city: String
)
