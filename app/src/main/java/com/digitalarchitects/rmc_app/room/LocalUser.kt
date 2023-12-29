package com.digitalarchitects.rmc_app.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.digitalarchitects.rmc_app.model.UserType

@Entity
data class LocalUser(
    val email: String,
    val userType: UserType = UserType.CLIENT,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val street: String,
    val buildingNumber: String,
    val zipCode: String,
    val city: String,
    val imageResourceId: Int? = 0,
    @PrimaryKey(autoGenerate = true)
    val id: Int
)
