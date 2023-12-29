package com.digitalarchitects.rmc_app.data.mapper

import com.digitalarchitects.rmc_app.model.Rental
import com.digitalarchitects.rmc_app.remote.dto.rental.RemoteRental
import com.digitalarchitects.rmc_app.room.LocalRental

fun List<LocalRental>.toRentalListFromLocal(): List<Rental> {
    return this.map { rental ->
        Rental(
            id = rental.id,
            vehicleId = rental.vehicleId,
            userId = rental.userId,
            date = rental.date,
            price = rental.price,
            latitude = rental.latitude,
            longitude = rental.longitude,
            status = rental.status,
            distanceTravelled = rental.distanceTravelled,
            score = rental.score
        )
    }
}

fun List<RemoteRental>.toRentalListFromRemote(): List<Rental> {
    return this.map { rental ->
        Rental(
            id = rental.id,
            vehicleId = rental.vehicleId,
            userId = rental.userId,
            date = rental.date,
            price = rental.price,
            latitude = rental.latitude,
            longitude = rental.longitude,
            status = rental.status,
            distanceTravelled = rental.distanceTravelled,
            score = rental.score
        )
    }
}


fun Rental.toLocalRental(): LocalRental {
    return LocalRental(
        vehicleId = vehicleId,
        userId = userId,
        date = date,
        price = price,
        latitude = latitude,
        longitude = longitude,
        status = status,
        distanceTravelled = distanceTravelled,
        score = score,
        id = id
    )
}

fun Rental.toRemoteRental(): RemoteRental {
    return RemoteRental(
        id = id,
        vehicleId = vehicleId,
        userId = userId,
        date = date,
        price = price,
        latitude = latitude,
        longitude = longitude,
        status = status,
        distanceTravelled = distanceTravelled,
        score = score
    )
}

fun LocalRental.toRental(): Rental {
    return Rental(
        id = id,
        vehicleId = vehicleId,
        userId = userId,
        date = date,
        price = price,
        latitude = latitude,
        longitude = longitude,
        status = status,
        distanceTravelled = distanceTravelled,
        score = score
    )
}

fun RemoteRental.toRental(): Rental {
    return Rental(
        id = id,
        vehicleId = vehicleId,
        userId = userId,
        date = date,
        price = price,
        latitude = latitude,
        longitude = longitude,
        status = status,
        distanceTravelled = distanceTravelled,
        score = score
    )
}

fun List<RemoteRental>.toLocalRentalListFromRemote(): List<LocalRental> {
    return this.map { rental ->
        LocalRental(
            id = rental.id,
            vehicleId = rental.vehicleId,
            userId = rental.userId,
            date = rental.date,
            price = rental.price,
            latitude = rental.latitude,
            longitude = rental.longitude,
            status = rental.status,
            distanceTravelled = rental.distanceTravelled,
            score = rental.score
        )
    }
}