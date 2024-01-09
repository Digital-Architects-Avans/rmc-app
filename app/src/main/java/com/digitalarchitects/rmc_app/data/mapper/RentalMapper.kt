package com.digitalarchitects.rmc_app.data.mapper

import com.digitalarchitects.rmc_app.data.local.LocalRental
import com.digitalarchitects.rmc_app.data.local.LocalUser
import com.digitalarchitects.rmc_app.data.local.LocalVehicle
import com.digitalarchitects.rmc_app.data.remote.dto.rental.RemoteRental
import com.digitalarchitects.rmc_app.domain.model.Rental
import com.digitalarchitects.rmc_app.domain.model.User
import com.digitalarchitects.rmc_app.domain.model.Vehicle

fun List<LocalRental>.toRentalListFromLocal(): List<Rental> {
    return this.map { rental ->
        Rental(
            rentalId = rental.rentalId,
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
            rentalId = rental.rentalId,
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

fun List<Rental>.toLocalRentalList(): List<LocalRental> {
    return this.map { rental ->
        LocalRental(
            rentalId = rental.rentalId,
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
        rentalId = rentalId,
        vehicleId = vehicleId,
        userId = userId,
        date = date,
        price = price,
        latitude = latitude,
        longitude = longitude,
        status = status,
        distanceTravelled = distanceTravelled,
        score = score,
    )
}

fun Rental.toRemoteRental(): RemoteRental {
    return RemoteRental(
        _id = rentalId,
        rentalId = rentalId,
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

fun RemoteRental.toLocalRental(): LocalRental {
    return LocalRental(
        rentalId = rentalId,
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
        rentalId = rentalId,
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
        rentalId = rentalId,
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
            rentalId = rental.rentalId,
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

fun Triple<LocalRental, LocalVehicle, LocalUser>.toTripleFromLocal(): Triple<Rental, Vehicle, User> {
    val rental = this.first.toRental()
    val vehicle = this.second.toVehicle()
    val user = this.third.toUser()

    return Triple(rental, vehicle, user)
}