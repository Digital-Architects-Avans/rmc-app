package com.digitalarchitects.rmc_app.fake

import com.digitalarchitects.rmc_app.data.auth.AuthResult
import com.digitalarchitects.rmc_app.data.auth.SignUpRequest
import com.digitalarchitects.rmc_app.data.remote.dto.user.SignupDTO
import com.digitalarchitects.rmc_app.data.remote.dto.user.UpdateUserDTO
import com.digitalarchitects.rmc_app.domain.model.User
import com.digitalarchitects.rmc_app.domain.repo.UserRepository

class FakeUserRepository : UserRepository {
    private val signInResults: MutableMap<Pair<String, String>, AuthResult<Unit>> = mutableMapOf()
    private val signUpResults: List<Any> = listOf()


    fun setSignInResult(email: String, password: String, result: AuthResult<Unit>) {
        signInResults[email to password] = result
    }

    fun setSignUpResult(email: String, password: String, result: AuthResult<Unit>) {
//        signUpResults[email to password] = result
    }

    override suspend fun signUp(request: SignUpRequest): AuthResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun signIn(username: String, password: String): AuthResult<Unit> {
        return signInResults[username to password] ?: AuthResult.UnknownError()
    }

    override suspend fun signOut() {
        TODO("Not yet implemented")
    }

    override suspend fun authenticate(): AuthResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllUsersFromLocalCache(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllUsersFromRemote() {
        TODO("Not yet implemented")
    }

    override suspend fun getUserById(userId: String): User {
        TODO("Not yet implemented")
    }

    override suspend fun addUser(user: User, signupDTO: SignupDTO) {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(userId: String, updatedUser: UpdateUserDTO) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(user: User): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getFirstName(userId: String): String? {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentUserIdFromDataStore(): String? {
        TODO("Not yet implemented")
    }

    override suspend fun setProfileImageSrc(userId: String, profileImageSrc: String) {
        TODO("Not yet implemented")
    }
}