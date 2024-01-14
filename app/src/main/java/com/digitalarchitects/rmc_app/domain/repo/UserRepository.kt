package com.digitalarchitects.rmc_app.domain.repo

import com.digitalarchitects.rmc_app.data.auth.AuthResult
import com.digitalarchitects.rmc_app.data.auth.SignUpRequest
import com.digitalarchitects.rmc_app.data.remote.dto.user.SignupDTO
import com.digitalarchitects.rmc_app.data.remote.dto.user.UpdateUserDTO
import com.digitalarchitects.rmc_app.domain.model.User

/**
 * Repository retrieves User data from underlying data source (remote and local).
 */
interface UserRepository {

    suspend fun signUp(request: SignUpRequest): AuthResult<Unit>
    suspend fun signIn(username: String, password: String): AuthResult<Unit>
    suspend fun signOut()
    suspend fun authenticate(): AuthResult<Unit>

    /** Retrieves list of [User] from underlying data source */
    suspend fun getAllUsers(): List<User>

    /** Retrieves list of [User] from local data source */
    suspend fun getAllUsersFromLocalCache(): List<User>

    /** Retrieves all [User] from remote data source and updates the local data source */
    suspend fun getAllUsersFromRemote()

    /** Retrieves [User] by id from underlying data source */
    suspend fun getUserById(userId: String): User

    /** Adds [User] to the underlying data source */
    suspend fun addUser(user: User, signupDTO: SignupDTO)

    /** Updates the [User] to the underlying data source */
    suspend fun updateUser(userId: String, updatedUser: UpdateUserDTO)

    /** Deletes the [User] from the underlying data source */
    suspend fun deleteUser(user: User): Result<Unit>

    /** Retrieves the first name of the [User] */
    suspend fun getFirstName(userId: String): String?

    /** Retrieves the userId of the [User] currently signed in from the DataStore */
    suspend fun getCurrentUserIdFromDataStore(): String?

    /** Sets the profileImageSrc of a [User] to the newly profile image source URL */
    suspend fun setProfileImageSrc(userId: String, profileImageSrc: String)

}