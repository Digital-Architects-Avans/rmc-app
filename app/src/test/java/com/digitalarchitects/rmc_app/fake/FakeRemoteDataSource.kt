package com.digitalarchitects.rmc_app.fake

import com.digitalarchitects.rmc_app.domain.model.EngineType
import com.digitalarchitects.rmc_app.domain.model.Rental
import com.digitalarchitects.rmc_app.domain.model.RentalStatus
import com.digitalarchitects.rmc_app.domain.model.User
import com.digitalarchitects.rmc_app.domain.model.UserType
import com.digitalarchitects.rmc_app.domain.model.Vehicle
import kotlinx.datetime.LocalDate

object FakeRemoteDataSource {
    val userList = listOf(
        User(
            userId = "1",
            email = "email",
            password = "password",
            salt = "salt",
            userType = UserType.STAFF,
            firstName = "firstName",
            lastName = "lastName",
            phone = "telephone",
            street = "street",
            buildingNumber = "buildingNumber",
            zipCode = "zipCode",
            city = "city"
        ),
        User(
            userId = "2",
            email = "email2",
            password = "password",
            salt = "salt",
            userType = UserType.CLIENT,
            firstName = "firstName2",
            lastName = "lastName2",
            phone = "telephone2",
            street = "street2",
            buildingNumber = "buildingNumber2",
            zipCode = "zipCode2",
            city = "city2"
        )
    )
    val vehicleList = listOf(
        Vehicle(
            vehicleId = "1",
            userId = "1",
            brand = "brand",
            model = "model",
            year = 1,
            vehicleClass = "vehicleClass1",
            engineType = EngineType.BEV,
            licensePlate = "licensePlate1",
            imgLink = 1,
            latitude = 2.0f,
            longitude = 2.0f,
            price = 2.0,
            availability = true,
            description = "placeholder",
            address = "City"
        ),
        Vehicle(
            vehicleId = "2",
            userId = "2",
            brand = "brand2",
            model = "model2",
            year = 2,
            vehicleClass = "vehicleClass2",
            engineType = EngineType.BEV,
            licensePlate = "licensePlate2",
            imgLink = 2,
            latitude = 2.0f,
            longitude = 2.0f,
            price = 2.0,
            availability = true,
            description = "placeholder",
            address = "City"
        )
    )
    val rentalList = listOf(
        Rental(
            rentalId = "1",
            vehicleId = "1",
            userId = "1",
            date = LocalDate(2021, 1, 1),
            price = 1.0,
            latitude = 1.0f,
            longitude = 1.0f,
            status = RentalStatus.PENDING,
            distanceTravelled = 1.0,
            score = 1
        ),
        Rental(
            rentalId = "1",
            vehicleId = "2",
            userId = "2",
            date = LocalDate(2021, 2, 2),
            price = 2.0,
            latitude = 2.0f,
            longitude = 2.0f,
            status = RentalStatus.APPROVED,
            distanceTravelled = 2.0,
            score = 2
        )
    )
}