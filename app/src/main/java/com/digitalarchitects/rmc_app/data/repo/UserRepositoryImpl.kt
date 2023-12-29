package com.digitalarchitects.rmc_app.data.repo

import android.util.Log
import com.digitalarchitects.rmc_app.RmcApplication
import com.digitalarchitects.rmc_app.data.di.IoDispatcher
import com.digitalarchitects.rmc_app.data.mapper.toLocalUser
import com.digitalarchitects.rmc_app.data.mapper.toLocalUserListFromRemote
import com.digitalarchitects.rmc_app.data.mapper.toRemoteUser
import com.digitalarchitects.rmc_app.data.mapper.toUser
import com.digitalarchitects.rmc_app.data.mapper.toUserListFromLocal
import com.digitalarchitects.rmc_app.domain.repo.UserRepository
import com.digitalarchitects.rmc_app.model.User
import com.digitalarchitects.rmc_app.remote.RmcApiService
import com.digitalarchitects.rmc_app.room.RmcRoomDatabaseRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

/**
 * Implementation of [UserRepository] that retrieves [User] data from underlying data sources.
 */
class UserRepositoryImpl(
    private val rmcRoomDatabase: RmcRoomDatabaseRepo,
    private val rmcApiService: RmcApiService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UserRepository {

    // TODO(): Add token dynamically to header after a user logs in
    // Currently use this global variable to store the token declared in [RmcApplication.GlobalVariables
    private val token = RmcApplication.GlobalVariables.token

    /** Fetches data from remote, updates local data source, returns users from local data source */
    override suspend fun getAllUsers(): List<User> {
        getAllUsersFromRemote()
        return rmcRoomDatabase.getAllUsersFromLocalDb().toUserListFromLocal()
    }

    /** Retrieves all [User] from local data source */
    override suspend fun getAllUsersFromLocalCache(): List<User> {
        return rmcRoomDatabase.getAllUsersFromLocalDb().toUserListFromLocal()
    }

    /** Retrieve all [User] from remote data source and update the local date source */
    override suspend fun getAllUsersFromRemote() {
        return withContext(dispatcher) {
            try {
                refreshRoomCache()
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException, is ConnectException, is HttpException -> {
                        Log.e("HTTP", "Error: No data from Remote")
                        Log.e("HTTP", "$e")
                        if (isCacheEmpty()) {
                            Log.e("Cache", "Error: No data from local Room cache")
                            throw Exception("Error: Device offline and\nno data from local Room cache")
                        }
                    }

                    else -> throw e
                }
            }
        }
    }

    // Update local Room cache with data from remote Retrofit API
    private suspend fun refreshRoomCache() {
        val remoteUsers = rmcApiService.getAllUsers(
            "Bearer $token"
        )
        rmcRoomDatabase.addAllUsersToLocalDb(remoteUsers.toLocalUserListFromRemote())
    }

    /** Returns true if local data source does not contain any [User] data */
    private suspend fun isCacheEmpty(): Boolean {
        var empty = true
        if (rmcRoomDatabase.getAllUsersFromLocalDb().isNotEmpty()) empty = false
        return empty
    }

    /** Returns [User] with ID from local data source*/
    override suspend fun getUserById(userId: Int): User {
        return rmcRoomDatabase.getUserByIdFromLocalDb(userId).toUser()
    }

    /** Adds new [User] to both remote and local data source */
    override suspend fun addUser(user: User) {
        val newId = rmcRoomDatabase.addUserToLocalDb(user.toLocalUser())
        val id = newId.toInt()
        rmcApiService.updateUser(
            "Bearer $token", id, user.toRemoteUser().copy(id = id)
        )
    }

    /** Updates [User] to both remote and local data source */
    override suspend fun updateUser(user: User) {
        rmcRoomDatabase.addUserToLocalDb(user.toLocalUser())
        rmcApiService.updateUser(
            "Bearer $token", user.id, user.toRemoteUser()
        )
    }

    /** Removes [User] from remote data source, local data source will get updated automatically */
    override suspend fun deleteUser(user: User): Result<Unit> {
        return try {
            val response = rmcApiService.deleteUser(
                "Bearer $token", user.id
            )

            if (response.isSuccessful) {
                Log.i("API_DELETE", "User deleted successfully: ${user.id}")
                Result.success(Unit)
            } else {
                val errorMessage = "Error deleting user: ${response.code()}\n${response.message()}"
                Log.e("API_DELETE", errorMessage)
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            when (e) {
                is UnknownHostException, is ConnectException, is HttpException -> {
                    Log.e("HTTP", "Error: Could not delete user", e)
                    Result.failure(e)
                }
                else -> {
                    Log.e("HTTP", "Unexpected error during user deletion", e)
                    Result.failure(e)
                }
            }
        }
    }

    /** Returns the first name of [User] */
    override suspend fun getFirstName(userId: Int): String? {
        return rmcRoomDatabase.getFirstName(userId)
    }
}