package com.digitalarchitects.rmc_app.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.digitalarchitects.rmc_app.model.UserType

@Entity
data class LocalUser(
    @PrimaryKey(autoGenerate = false)
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
    val imageResourceId: Int? = 0
)