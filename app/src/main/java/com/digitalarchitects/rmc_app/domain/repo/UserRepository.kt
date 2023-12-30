package com.digitalarchitects.rmc_app.domain.repo

import com.digitalarchitects.rmc_app.model.User
import com.digitalarchitects.rmc_app.remote.dto.user.SigninDTO
import com.digitalarchitects.rmc_app.remote.dto.user.SignupDTO

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
    suspend fun addUser(user: User, signupDTO: SignupDTO)

    /** Authenticates a [User] to the underlying data source */
    suspend fun authenticateUser(signinDTO: SigninDTO)

    /** Updates the [User] to the underlying data source */
    suspend fun updateUser(user: User)

    /** Deletes the [User] from the underlying data source */
    suspend fun deleteUser(user: User): Result<Unit>

    /** Retrieves the first name of the [User] */
    suspend fun getFirstName(userId: Int): String?

}