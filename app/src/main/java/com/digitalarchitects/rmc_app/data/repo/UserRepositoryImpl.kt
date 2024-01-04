package com.digitalarchitects.rmc_app.data.repo

import android.util.Base64
import android.util.Log
import com.digitalarchitects.rmc_app.data.auth.AuthRequest
import com.digitalarchitects.rmc_app.data.auth.AuthResult
import com.digitalarchitects.rmc_app.data.auth.SignUpRequest
import com.digitalarchitects.rmc_app.data.di.IoDispatcher
import com.digitalarchitects.rmc_app.data.local.RmcRoomDatabaseRepo
import com.digitalarchitects.rmc_app.data.mapper.toLocalUser
import com.digitalarchitects.rmc_app.data.mapper.toUser
import com.digitalarchitects.rmc_app.data.mapper.toUserListFromLocal
import com.digitalarchitects.rmc_app.data.remote.RmcApiService
import com.digitalarchitects.rmc_app.data.remote.dto.user.SignupDTO
import com.digitalarchitects.rmc_app.data.remote.dto.user.UpdateUserDTO
import com.digitalarchitects.rmc_app.domain.model.User
import com.digitalarchitects.rmc_app.domain.repo.UserPreferencesRepository
import com.digitalarchitects.rmc_app.domain.repo.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Implementation of [UserRepository] that retrieves [User] data from underlying data sources.
 */
class UserRepositoryImpl @Inject constructor(
    private val rmcRoomDatabase: RmcRoomDatabaseRepo,
    private val rmcApiService: RmcApiService,
    private val userPreferencesRepository: UserPreferencesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UserRepository {

    override suspend fun signUp(request: SignUpRequest): AuthResult<Unit> {
        return try {
            rmcApiService.signup(
                 request = SignUpRequest(
                    email = request.email,
                    password = request.password,
                    userType = request.userType,
                    firstName = request.firstName,
                    lastName = request.lastName,
                    phone = request.phone,
                    street = request.street,
                    buildingNumber = request.buildingNumber,
                    zipCode = request.zipCode,
                    city = request.city,
                )
            )
            signIn(request.email, request.password)
        } catch (e: HttpException) {
            if (e.code() == 401 || e.code() == 404) {
                Log.e("HTTP", "Error: ${e.code()}\n${e.message()}")
                AuthResult.Unauthorized()
            } else {
                Log.e("HTTP", "Error: ${e.code()}\n${e.message()}")
                AuthResult.UnknownError()
            }
        } catch (e: ConnectException) {
            Log.e("HTTP", "Error: $e")
            AuthResult.NoConnectionError()
        } catch (e: Exception) {
            Log.e("HTTP", "Error: $e")
            AuthResult.UnknownError()
        }
    }

    override suspend fun signIn(username: String, password: String): AuthResult<Unit> {
        return try {
            val response = rmcApiService.signin(
                request = AuthRequest(
                    email = username,
                    password = password
                )
            )
            // Save the JWT token to the Datastore using the userPreferencesRepository
            userPreferencesRepository.saveJwt(response.token)

            // Get userId from JWT
            val payload = response.token.split(".")[1]
            val decodedBytes = Base64.decode(payload, Base64.URL_SAFE)
            val decodedString = String(decodedBytes)
            val jsonObject = JSONObject(decodedString)
            val userId = jsonObject.optString("userId", null)

            // Store userId in Datastore
            userPreferencesRepository.saveUserId(userId)

            AuthResult.Authorized()
        } catch (e: HttpException) {
            if (e.code() == 401 || e.code() == 404) {
                Log.e("HTTP", "Error: ${e.code()}\n${e.message()}")
                AuthResult.Unauthorized()
            } else {
                Log.e("HTTP", "Error: ${e.code()}\n${e.message()}")
                AuthResult.UnknownError()
            }
        } catch (e: ConnectException) {
            Log.e("HTTP", "Error: $e")
            AuthResult.NoConnectionError()
        } catch (e: Exception) {
            Log.e("HTTP", "Error: $e")
            AuthResult.UnknownError()
        }
    }

    // Authenticate user using the JWT token stored in shared preferences
    override suspend fun authenticate(): AuthResult<Unit> {
        return try {
            // If no token present in shared preferences, return Unauthorized
            val token = userPreferencesRepository.getJwtToken() ?: return AuthResult.Unauthorized()
            rmcApiService.authenticate()
            AuthResult.Authorized()
        } catch (e: HttpException) {
            if (e.code() == 401) {
                Log.e("HTTP", "Error: ${e.code()}\n${e.message()}")
                AuthResult.Unauthorized()
            } else {
                Log.e("HTTP", "Error: ${e.code()}\n${e.message()}")
                AuthResult.UnknownError()
            }
        } catch (e: ConnectException) {
            Log.e("HTTP", "Error: $e")
            AuthResult.NoConnectionError()
        } catch (e: Exception) {
            Log.e("HTTP", "Error: $e")
            AuthResult.UnknownError()
        }
    }

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
        val users = rmcApiService.getAllUsers()
        rmcRoomDatabase.clearUserCache()
        rmcRoomDatabase.addAllUsersToLocalDb(users.toLocalUser())
    }

    /** Returns true if local data source does not contain any [User] data */
    private suspend fun isCacheEmpty(): Boolean {
        var empty = true
        if (rmcRoomDatabase.getAllUsersFromLocalDb().isNotEmpty()) empty = false
        return empty
    }

    /** Returns [User] with ID from local data source*/
    override suspend fun getUserById(userId: String): User {
        return rmcRoomDatabase.getUserByIdFromLocalDb(userId).toUser()
    }

    /** Adds new [User] to both remote and local data source */
    override suspend fun addUser(user: User, signupDTO: SignupDTO) {
        rmcRoomDatabase.addUserToLocalDb(user.toLocalUser())
//        rmcApiService.addUser(
//            signupDTO
//        )
    }

    /** Updates [User] to both remote and local data source */
    override suspend fun updateUser(userId: String, updatedUser: UpdateUserDTO) {
        // Update remote data source
        // Remove user from local data source
        // Add updated user to local data source
        rmcApiService.updateUser(userId, updatedUser)
    }

    /** Removes [User] from remote data source, local data source will get updated automatically */
    override suspend fun deleteUser(user: User): Result<Unit> {
        return try {
            val response = rmcApiService.deleteUser(user.userId)

            if (response.isSuccessful) {
                Log.i("API_DELETE", "User deleted successfully: ${user.userId}")
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
    override suspend fun getFirstName(userId: String): String? {
        return rmcRoomDatabase.getFirstName(userId)
    }

    override suspend fun getCurrentUserIdFromDataStore(): String? {
        return userPreferencesRepository.getUserId()
    }
}