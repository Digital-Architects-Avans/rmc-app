package com.digitalarchitects.rmc_app.dummyDTO

import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.model.EngineType
import com.digitalarchitects.rmc_app.model.Vehicle
import java.math.BigDecimal

/**
 * Fake dummy data used for development and testing until DTO and API connection is realised
 */
fun DummyVehicleDTO(): List<Vehicle> {
    return listOf(
        Vehicle(
            id = 1,
            userId = 1, // Assuming you have a userId associated with each vehicle
            brand = "Toyota",
            model = "Yaris",
            year = 2011,
            vehicleClass = "Compact",
            engineType = EngineType.ICE,
            licensePlate = "YW - 790 - 2",
            imgLink = R.drawable.yaris,
            latitude = BigDecimal("51.4416"),
            longitude = BigDecimal("5.4697"),
            price = 95.0,
            availability = true
        ),
        Vehicle(
            id = 2,
            userId = 1,
            brand = "Honda",
            model = "Civic",
            year = 2020,
            vehicleClass = "Sedan",
            engineType = EngineType.ICE,
            licensePlate = "ABC - 123",
            imgLink = R.drawable.civic,
            latitude = BigDecimal("52.3676"),
            longitude = BigDecimal("4.9041"),
            price = 120.0,
            availability = false
        ),
        Vehicle(
            id = 3,
            userId = 2, // Assuming a different userId for this vehicle
            brand = "Ford",
            model = "Focus",
            year = 2019,
            vehicleClass = "Hatchback",
            engineType = EngineType.ICE,
            licensePlate = "XYZ - 456",
            imgLink = R.drawable.focus,
            latitude = BigDecimal("51.9225"),
            longitude = BigDecimal("4.4794"),
            price = 80.0,
            availability = true
        ),
        Vehicle(
            id = 4,
            userId = 2,
            brand = "Chevrolet",
            model = "Malibu",
            year = 2022,
            vehicleClass = "Sedan",
            engineType = EngineType.ICE,
            licensePlate = "DEF - 789",
            imgLink = R.drawable.malibu,
            latitude = BigDecimal("52.0907"),
            longitude = BigDecimal("5.1214"),
            price = 110.0,
            availability = true
        )
    )

}
