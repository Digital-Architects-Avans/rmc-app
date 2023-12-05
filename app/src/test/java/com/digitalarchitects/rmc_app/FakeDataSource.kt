package com.digitalarchitects.rmc_app

import com.digitalarchitects.rmc_app.model.User

object FakeDataSource {
    val userList = listOf(
        User(
            firstName = "firstName",
            lastName = "lastName",
            email = "email",
            telephone = "telephone",
            password = "password",
            address = "address",
            postalCode = "postalCode",
            buildingNumber = "buildingNumber",
            city = "city"
        ),
        User(
            firstName = "firstName2",
            lastName = "lastName2",
            email = "email2",
            telephone = "telephone2",
            password = "password2",
            address = "address2",
            postalCode = "postalCode2",
            buildingNumber = "buildingNumber2",
            city = "city2"
        )
    )
}