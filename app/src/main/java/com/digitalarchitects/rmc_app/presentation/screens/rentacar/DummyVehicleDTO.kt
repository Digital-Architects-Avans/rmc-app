package com.digitalarchitects.rmc_app.presentation.screens.rentacar

import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.domain.model.EngineType
import com.digitalarchitects.rmc_app.domain.model.Vehicle

//TODO("Remove this dummy data when DTO and API connection is realised inside the vehicle screens")
/**
 * Fake dummy data used for development and testing until DTO and API connection is realised
 */
fun DummyVehicleDTO(): List<Vehicle> {
    return listOf(
        Vehicle(
            vehicleId = "1",
            userId = 1, // Assuming you have a userId associated with each vehicle
            brand = "Toyota",
            model = "Yaris",
            year = 2011,
            vehicleClass = "Compact",
            engineType = EngineType.ICE,
            licensePlate = "YW - 790 - 2",
            imgLink = R.drawable.yaris,
            latitude = 51.5836F,
            longitude = 4.7971F,
            price = 95.0,
            availability = true
        ),
        Vehicle(
            vehicleId = "2",
            userId = 1,
            brand = "Honda",
            model = "Civic",
            year = 2020,
            vehicleClass = "Sedan",
            engineType = EngineType.ICE,
            licensePlate = "ABC - 123",
            imgLink = R.drawable.civic,
            latitude = 51.5857F,
            longitude = 4.7932F,
            price = 120.0,
            availability = false
        ),
        Vehicle(
            vehicleId = "3",
            userId = 2, // Assuming a different userId for this vehicle
            brand = "Ford",
            model = "Focus",
            year = 2019,
            vehicleClass = "Hatchback",
            engineType = EngineType.ICE,
            licensePlate = "XYZ - 456",
            imgLink = R.drawable.focus,
            latitude = 51.5865F,
            longitude = 4.7759F,
            price = 80.0,
            availability = true
        ),
        Vehicle(
            vehicleId = "4",
            userId = 2,
            brand = "Chevrolet",
            model = "Malibu",
            year = 2022,
            vehicleClass = "Sedan",
            engineType = EngineType.ICE,
            licensePlate = "DEF - 789",
            imgLink = R.drawable.malibu,
            latitude = 51.5794F,
            longitude = 4.8109F,
            price = 110.0,
            availability = true
        )
    )

}