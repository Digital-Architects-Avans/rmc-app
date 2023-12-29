package com.digitalarchitects.rmc_app.dummyDTO

import com.digitalarchitects.rmc_app.model.Rental
import com.digitalarchitects.rmc_app.model.RentalStatus
import kotlinx.datetime.LocalDate

fun DummyRentalDTO(): List<Rental> {
    return listOf(
        Rental(
            id = 1,
            vehicleId = 1,
            userId = 1,
            date = LocalDate(23, 1,1),
            price = 80.0,
            latitude = 51.4416F,
            longitude = 5.4697F,
            status = RentalStatus.PENDING,
            distanceTravelled = 50.0,
            score = 0
        ),
        Rental(
            id = 2,
            vehicleId = 2,
            userId = 2,
            date = LocalDate(23, 1,1),
            price = 120.0,
            latitude = 51.4416F,
            longitude = 5.4697F,
            status = RentalStatus.APPROVED,
            distanceTravelled = 75.0,
            score = 4
        ),
        Rental(
            id = 2,
            vehicleId = 2,
            userId = 2,
            date = LocalDate(23, 1,1),
            price = 120.0,
            latitude = 51.4416F,
            longitude = 5.4697F,
            status = RentalStatus.APPROVED,
            distanceTravelled = 75.0,
            score = 4
        ),
        Rental(
            id = 3,
            vehicleId = 3,
            userId = 1,
            date = LocalDate(23, 1,1),
            price = 95.0,
            latitude = 51.4416F,
            longitude = 5.4697F,
            status = RentalStatus.DENIED,
            distanceTravelled = 60.0,
            score = 2
        ),
        Rental(
            id = 4,
            vehicleId = 4,
            userId = 2,
            date = LocalDate(23, 1,1),
            price = 110.0,
            latitude = 51.4416F,
            longitude = 5.4697F,
            status = RentalStatus.CANCELLED,
            distanceTravelled = 45.0,
            score = 5
        ),
        Rental(
            id = 5,
            vehicleId = 1,
            userId = 1,
            date = LocalDate(23, 1,1),
            price = 80.0,
            latitude = 51.4416F,
            longitude = 5.4697F,
            status = RentalStatus.PENDING,
            distanceTravelled = 50.0,
            score = 0
        ),
        Rental(
            id = 6,
            vehicleId = 2,
            userId = 2,
            date = LocalDate(23, 1,1),
            price = 120.0,
            latitude = 51.4416F,
            longitude = 5.4697F,
            status = RentalStatus.APPROVED,
            distanceTravelled = 75.0,
            score = 4
        ),
        Rental(
            id = 7,
            vehicleId = 3,
            userId = 1,
            date = LocalDate(23, 1,1),
            price = 95.0,
            latitude = 51.4416F,
            longitude = 5.4697F,
            status = RentalStatus.DENIED,
            distanceTravelled = 60.0,
            score = 2
        ),
        Rental(
            id = 8,
            vehicleId = 4,
            userId = 2,
            date = LocalDate(23, 1,1),
            price = 110.0,
            latitude = 51.4416F,
            longitude = 5.4697F,
            status = RentalStatus.APPROVED,
            distanceTravelled = 45.0,
            score = 5
        )
    )
}