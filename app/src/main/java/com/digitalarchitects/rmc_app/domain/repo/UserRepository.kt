package com.digitalarchitects.rmc_app.domain.repo

import com.digitalarchitects.rmc_app.model.User

/**
 * Repository retrieves User data from underlying data source (remote and local).
 */
interface UserRepository {

    /** Retrieves list of [User] from underlying data source */
    suspend fun getAllUsers(): List<User>

    /** Retrieves list of [User] from local data source */
    suspend fun getAllUsersFromLocalCache(): List<User>

    /** Retrieves all [User] from remote data source and updates the local data source */
    suspend fun getAllUsersFromRemote()

    /** Retrieves [User] by id from underlying data source */
    suspend fun getUserById(userId: Int): User

    /** Adds [User] to the underlying data source */
    suspend fun addUser(user: User)

    /** Updates the [User] to the underlying data source */
    suspend fun updateUser(user: User)

    /** Deletes the [User] from the underlying data source */
    suspend fun deleteUser(user: User): Result<Unit>

    /** Retrieves the first name of the [User] */
    suspend fun getFirstName(userId: Int): String?

}