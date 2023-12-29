package com.digitalarchitects.rmc_app.data.mapper

import com.digitalarchitects.rmc_app.model.User
import com.digitalarchitects.rmc_app.remote.dto.user.RemoteUser
import com.digitalarchitects.rmc_app.room.LocalUser

fun List<LocalUser>.toUserListFromLocal(): List<User> {
    return this.map { user ->
        User(
            id = user.id,
            email = user.email,
            userType = user.userType,
            firstName = user.firstName,
            lastName = user.lastName,
            phone = user.phone,
            street = user.street,
            buildingNumber = user.buildingNumber,
            zipCode = user.zipCode,
            city = user.city,
            imageResourceId = user.imageResourceId
        )
    }
}

fun List<RemoteUser>.toUserListFromRemote(): List<User> {
    return this.map { user ->
        User(
            id = user.id,
            email = user.email,
            userType = user.userType,
            firstName = user.firstName,
            lastName = user.lastName,
            phone = user.phone,
            street = user.street,
            buildingNumber = user.buildingNumber,
            zipCode = user.zipCode,
            city = user.city,
            imageResourceId = user.imageResourceId
        )
    }
}


fun User.toLocalUser(): LocalUser {
    return LocalUser(
        id = id,
        email = email,
        userType = userType,
        firstName = firstName,
        lastName = lastName,
        phone = phone,
        street = street,
        buildingNumber = buildingNumber,
        zipCode = zipCode,
        city = city,
        imageResourceId = imageResourceId
    )
}

fun User.toRemoteUser(): RemoteUser {
    return RemoteUser(
        id = id,
        email = email,
        userType = userType,
        firstName = firstName,
        lastName = lastName,
        phone = phone,
        street = street,
        buildingNumber = buildingNumber,
        zipCode = zipCode,
        city = city
    )
}

fun LocalUser.toUser(): User {
    return User(
        id = id,
        email = email,
        userType = userType,
        firstName = firstName,
        lastName = lastName,
        phone = phone,
        street = street,
        buildingNumber = buildingNumber,
        zipCode = zipCode,
        city = city,
        imageResourceId = imageResourceId
    )
}

fun RemoteUser.toUser(): User {
    return User(
        id = id,
        email = email,
        userType = userType,
        firstName = firstName,
        lastName = lastName,
        phone = phone,
        street = street,
        buildingNumber = buildingNumber,
        zipCode = zipCode,
        city = city,
        imageResourceId = imageResourceId
    )
}

fun List<RemoteUser>.toLocalUserListFromRemote(): List<LocalUser> {
    return this.map { user ->
        LocalUser(
            id = user.id,
            email = user.email,
            userType = user.userType,
            firstName = user.firstName,
            lastName = user.lastName,
            phone = user.phone,
            street = user.street,
            buildingNumber = user.buildingNumber,
            zipCode = user.zipCode,
            city = user.city
        )
    }
}