package com.digitalarchitects.rmc_app.model

import androidx.annotation.DrawableRes

enum class UserType {
    STAFF, CLIENT, OTHER
}

data class User(
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
    @DrawableRes val imageResourceId: Int = 0
)
