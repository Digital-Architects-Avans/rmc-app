package com.digitalarchitects.rmc_app.dummyDTO

import com.digitalarchitects.rmc_app.model.Rental
import com.digitalarchitects.rmc_app.model.RentalStatus
import java.math.BigDecimal
import java.time.LocalDate

fun DummyRentalDTO(): List<Rental> {
    return listOf(
        Rental(
            id = 1,
            vehicleId = 1,
            userId = 1,
            date = LocalDate.now(),
            price = 80.0,
            latitude = BigDecimal("51.4416"),
            longitude = BigDecimal("5.4697"),
            status = RentalStatus.PENDING,
            distanceTravelled = 50.0,
            score = 0
        ),
        Rental(
            id = 2,
            vehicleId = 2,
            userId = 2,
            date = LocalDate.now().plusDays(1),
            price = 120.0,
            latitude = BigDecimal("52.3676"),
            longitude = BigDecimal("4.9041"),
            status = RentalStatus.APPROVED,
            distanceTravelled = 75.0,
            score = 4
        ),
        Rental(
            id = 2,
            vehicleId = 2,
            userId = 2,
            date = LocalDate.now().minusDays(1),
            price = 120.0,
            latitude = BigDecimal("52.3676"),
            longitude = BigDecimal("4.9041"),
            status = RentalStatus.APPROVED,
            distanceTravelled = 75.0,
            score = 4
        ),
        Rental(
            id = 3,
            vehicleId = 3,
            userId = 1,
            date = LocalDate.now().minusDays(2),
            price = 95.0,
            latitude = BigDecimal("51.9225"),
            longitude = BigDecimal("4.4794"),
            status = RentalStatus.DENIED,
            distanceTravelled = 60.0,
            score = 2
        ),
        Rental(
            id = 4,
            vehicleId = 4,
            userId = 2,
            date = LocalDate.now().minusDays(3),
            price = 110.0,
            latitude = BigDecimal("52.0907"),
            longitude = BigDecimal("5.1214"),
            status = RentalStatus.CANCELLED,
            distanceTravelled = 45.0,
            score = 5
        ),
        Rental(
            id = 5,
            vehicleId = 1,
            userId = 1,
            date = LocalDate.now().plusDays(4),
            price = 80.0,
            latitude = BigDecimal("51.4416"),
            longitude = BigDecimal("5.4697"),
            status = RentalStatus.PENDING,
            distanceTravelled = 50.0,
            score = 0
        ),
        Rental(
            id = 6,
            vehicleId = 2,
            userId = 2,
            date = LocalDate.now().minusDays(5),
            price = 120.0,
            latitude = BigDecimal("52.3676"),
            longitude = BigDecimal("4.9041"),
            status = RentalStatus.APPROVED,
            distanceTravelled = 75.0,
            score = 4
        ),
        Rental(
            id = 7,
            vehicleId = 3,
            userId = 1,
            date = LocalDate.now().plusDays(6),
            price = 95.0,
            latitude = BigDecimal("51.9225"),
            longitude = BigDecimal("4.4794"),
            status = RentalStatus.DENIED,
            distanceTravelled = 60.0,
            score = 2
        ),
        Rental(
            id = 8,
            vehicleId = 4,
            userId = 2,
            date = LocalDate.now().plusDays(7),
            price = 110.0,
            latitude = BigDecimal("52.0907"),
            longitude = BigDecimal("5.1214"),
            status = RentalStatus.APPROVED,
            distanceTravelled = 45.0,
            score = 5
        )
    )
}