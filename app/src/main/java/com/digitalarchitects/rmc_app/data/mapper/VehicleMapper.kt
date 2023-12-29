package com.digitalarchitects.rmc_app.data.mapper

import com.digitalarchitects.rmc_app.model.Vehicle
import com.digitalarchitects.rmc_app.remote.dto.vehicle.RemoteVehicle
import com.digitalarchitects.rmc_app.room.LocalVehicle

fun List<LocalVehicle>.toVehicleListFromLocal(): List<Vehicle>{
    return this.map { vehicle ->
        Vehicle(
            id = vehicle.id,
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
            id = vehicle.id,
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
        availability = availability,
        id = id
    )
}

fun Vehicle.toRemoteVehicle(): RemoteVehicle {
    return RemoteVehicle(
        id = id,
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
        id = id,
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
        id = id,
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
            availability = vehicle.availability,
            id = vehicle.id
        )
    }
}