package com.digitalarchitects.rmc_app.data.mapper

import com.digitalarchitects.rmc_app.data.local.LocalUser
import com.digitalarchitects.rmc_app.data.remote.dto.user.RemoteUser
import com.digitalarchitects.rmc_app.domain.model.User

fun List<User>.toLocalUser(): List<LocalUser> {
    return this.map { user ->
        LocalUser(
            userId = user.userId,
            email = user.email,
            password = user.password,
            salt = user.salt,
            userType = user.userType,
            firstName = user.firstName,
            lastName = user.lastName,
            phone = user.phone,
            street = user.street,
            buildingNumber = user.buildingNumber,
            zipCode = user.zipCode,
            city = user.city,
            profileImageSrc = user.profileImageSrc
        )
    }
}

fun List<LocalUser>.toUserListFromLocal(): List<User> {
    return this.map { user ->
        User(
            userId = user.userId,
            email = user.email,
            password = user.password,
            salt = user.salt,
            userType = user.userType,
            firstName = user.firstName,
            lastName = user.lastName,
            phone = user.phone,
            street = user.street,
            buildingNumber = user.buildingNumber,
            zipCode = user.zipCode,
            city = user.city,
            profileImageSrc = user.profileImageSrc
        )
    }
}

fun List<RemoteUser>.toUserListFromRemote(): List<User> {
    return this.map { user ->
        User(
            userId = user.userId,
            email = user.email,
            password = user.password,
            salt = user.salt,
            userType = user.userType,
            firstName = user.firstName,
            lastName = user.lastName,
            phone = user.phone,
            street = user.street,
            buildingNumber = user.buildingNumber,
            zipCode = user.zipCode,
            city = user.city,
            profileImageSrc = user.profileImageSrc
        )
    }
}


fun User.toLocalUser(): LocalUser {
    return LocalUser(
        userId = userId,
        email = email,
        password = password,
        salt = salt,
        userType = userType,
        firstName = firstName,
        lastName = lastName,
        phone = phone,
        street = street,
        buildingNumber = buildingNumber,
        zipCode = zipCode,
        city = city,
        profileImageSrc = profileImageSrc
    )
}

fun User.toRemoteUser(): RemoteUser {
    return RemoteUser(
        _id = userId,
        userId = userId,
        email = email,
        password = password,
        salt = salt,
        userType = userType,
        firstName = firstName,
        lastName = lastName,
        phone = phone,
        street = street,
        buildingNumber = buildingNumber,
        zipCode = zipCode,
        city = city,
        profileImageSrc = profileImageSrc
    )
}

fun LocalUser.toUser(): User {
    return User(
        userId = userId,
        email = email,
        password = password,
        salt = salt,
        userType = userType,
        firstName = firstName,
        lastName = lastName,
        phone = phone,
        street = street,
        buildingNumber = buildingNumber,
        zipCode = zipCode,
        city = city,
        profileImageSrc = profileImageSrc
    )
}

fun RemoteUser.toUser(): User {
    return User(
        userId = userId,
        email = email,
        password = password,
        salt = salt,
        userType = userType,
        firstName = firstName,
        lastName = lastName,
        phone = phone,
        street = street,
        buildingNumber = buildingNumber,
        zipCode = zipCode,
        city = city,
        profileImageSrc = profileImageSrc
    )
}

fun List<RemoteUser>.toLocalUserListFromRemote(): List<LocalUser> {
    return this.map { user ->
        LocalUser(
            userId = user.userId,
            email = user.email,
            password = user.password,
            salt = user.salt,
            userType = user.userType,
            firstName = user.firstName,
            lastName = user.lastName,
            phone = user.phone,
            street = user.street,
            buildingNumber = user.buildingNumber,
            zipCode = user.zipCode,
            city = user.city,
            profileImageSrc = user.profileImageSrc
        )
    }
}