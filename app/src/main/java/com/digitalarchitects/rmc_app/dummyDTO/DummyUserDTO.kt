package com.digitalarchitects.rmc_app.dummyDTO

import com.digitalarchitects.rmc_app.model.User
import com.digitalarchitects.rmc_app.model.UserType

/**
 * Fake dummy data used for development and testing until DTO and API connection is realised
 */
fun DummyUserDTO(): User {
    return User(
        userId = "1", // Replace with an actual user ID
        email = "john.doe@example.com",
        password = "password",
        salt = "salt",
        userType = UserType.CLIENT, // Replace with the appropriate user type
        firstName = "John",
        lastName = "Doe",
        phone = "123-456-7890",
        street = "123 Main Street",
        buildingNumber = "A1",
        zipCode = "12345",
        city = "Example City"
    )
}

