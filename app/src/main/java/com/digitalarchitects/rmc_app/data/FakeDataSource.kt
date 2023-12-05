package com.digitalarchitects.rmc_app.data

import com.digitalarchitects.rmc_app.model.User
import com.digitalarchitects.rmc_app.model.Vehicle

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
    val vehicleList = listOf(
        Vehicle(
            plateNumber = "plateNumber",
            status = "status",
            model = "model",
            location = "location",
            price = "price",
            img = 1
        ),
        Vehicle(
            plateNumber = "plateNumber2",
            status = "status2",
            model = "model2",
            location = "location2",
            price = "price2",
            img = 2
        )
    )
}