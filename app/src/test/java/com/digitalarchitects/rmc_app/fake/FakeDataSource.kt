package com.digitalarchitects.rmc_app.fake

import com.digitalarchitects.rmc_app.model.User
import com.digitalarchitects.rmc_app.model.Vehicle

object FakeDataSource {
    val userList = listOf(
        User(
            id = 1,
            email = "email",
            userType = "userType",
            firstName = "firstName",
            lastName = "lastName",
            phone = "telephone",
            street = "street",
            buildingNumber = "buildingNumber",
            zipCode = "zipCode",
            city = "city"
        ),
        User(
            id = 2,
            email = "email2",
            userType = "userType2",
            firstName = "firstName2",
            lastName = "lastName2",
            phone = "telephone2",
            street = "street2",
            buildingNumber = "buildingNumber2",
            zipCode = "zipCode2",
            city = "city2"
        ),
    )
    val vehicleList = listOf(
        Vehicle(
            id = 1,
            userId = 1,
            brand = "brand",
            model = "model",
            year = 1,
            vehicleClass = "vehicleClass1",
            engineType = "engineType1",
            licensePlate = "licensePlate1",
            imgLink = "imgLink1",
            latitude = 2.0f,
            longitude = 2.0f,
            price = 2.0,
            availability = true
        ),
        Vehicle(
            id = 2,
            userId = 2,
            brand = "brand2",
            model = "model2",
            year = 2,
            vehicleClass = "vehicleClass2",
            engineType = "engineType2",
            licensePlate = "licensePlate2",
            imgLink = "imgLink2",
            latitude = 2.0f,
            longitude = 2.0f,
            price = 2.0,
            availability = true
        )
    )
}