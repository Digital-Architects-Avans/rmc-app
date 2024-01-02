package com.digitalarchitects.rmc_app.data.mapper

import com.digitalarchitects.rmc_app.model.Vehicle
import com.digitalarchitects.rmc_app.remote.dto.vehicle.RemoteVehicle
import com.digitalarchitects.rmc_app.room.LocalVehicle

fun List<LocalVehicle>.toVehicleListFromLocal(): List<Vehicle>{
    return this.map { vehicle ->
        Vehicle(
            vehicleId = vehicle.vehicleId,
            userId = vehicle.userId,
            brand = vehicle.brand,
            model = vehicle.model,
            year = vehicle.year,
            vehicleClass = vehicle.vehicleClass,
            engineType = vehicle.engineType,
            licensePlate = vehicle.licensePlate,
            imgLink = vehicle.imgLink,
            latitude = vehicle.latitude,
            longitude = vehicle.longitude,
            price = vehicle.price,
            availability = vehicle.availability
        )
    }
}

fun List<RemoteVehicle>.toVehicleListFromRemote(): List<Vehicle>{
    return this.map { vehicle ->
        Vehicle(
            vehicleId = vehicle.vehicleId,
            userId = vehicle.userId,
            brand = vehicle.brand,
            model = vehicle.model,
            year = vehicle.year,
            vehicleClass = vehicle.vehicleClass,
            engineType = vehicle.engineType,
            licensePlate = vehicle.licensePlate,
            imgLink = vehicle.imgLink,
            latitude = vehicle.latitude,
            longitude = vehicle.longitude,
            price = vehicle.price,
            availability = vehicle.availability
        )
    }
}


fun Vehicle.toLocalVehicle(): LocalVehicle {
    return LocalVehicle(
        vehicleId = vehicleId,
        userId = userId,
        brand = brand,
        model = model,
        year = year,
        vehicleClass = vehicleClass,
        engineType = engineType,
        licensePlate = licensePlate,
        imgLink = imgLink,
        latitude = latitude,
        longitude = longitude,
        price = price,
        availability = availability
    )
}

fun Vehicle.toRemoteVehicle(): RemoteVehicle {
    return RemoteVehicle(
        objectId = vehicleId,
        vehicleId = vehicleId,
        userId = userId,
        brand = brand,
        model = model,
        year = year,
        vehicleClass = vehicleClass,
        engineType = engineType,
        licensePlate = licensePlate,
        imgLink = imgLink,
        latitude = latitude,
        longitude = longitude,
        price = price,
        availability = availability
    )
}

fun LocalVehicle.toVehicle(): Vehicle{
    return Vehicle(
        vehicleId = vehicleId,
        userId = userId,
        brand = brand,
        model = model,
        year = year,
        vehicleClass = vehicleClass,
        engineType = engineType,
        licensePlate = licensePlate,
        imgLink = imgLink,
        latitude = latitude,
        longitude = longitude,
        price = price,
        availability = availability
    )
}

fun RemoteVehicle.toVehicle(): Vehicle{
    return Vehicle(
        vehicleId = vehicleId,
        userId = userId,
        brand = brand,
        model = model,
        year = year,
        vehicleClass = vehicleClass,
        engineType = engineType,
        licensePlate = licensePlate,
        imgLink = imgLink,
        latitude = latitude,
        longitude = longitude,
        price = price,
        availability = availability
    )
}

fun List<RemoteVehicle>.toLocalVehicleListFromRemote(): List<LocalVehicle>{
    return this.map { vehicle ->
        LocalVehicle(
            vehicleId = vehicle.vehicleId,
            userId = vehicle.userId,
            brand = vehicle.brand,
            model = vehicle.model,
            year = vehicle.year,
            vehicleClass = vehicle.vehicleClass,
            engineType = vehicle.engineType,
            licensePlate = vehicle.licensePlate,
            imgLink = vehicle.imgLink,
            latitude = vehicle.latitude,
            longitude = vehicle.longitude,
            price = vehicle.price,
            availability = vehicle.availability
        )
    }
}

fun List<Vehicle>.toLocalVehicleList(): List<LocalVehicle>{
    return this.map { vehicle ->
        LocalVehicle(
            vehicleId = vehicle.vehicleId,
            userId = vehicle.userId,
            brand = vehicle.brand,
            model = vehicle.model,
            year = vehicle.year,
            vehicleClass = vehicle.vehicleClass,
            engineType = vehicle.engineType,
            licensePlate = vehicle.licensePlate,
            imgLink = vehicle.imgLink,
            latitude = vehicle.latitude,
            longitude = vehicle.longitude,
            price = vehicle.price,
            availability = vehicle.availability
        )
    }
}