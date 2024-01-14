package com.digitalarchitects.rmc_app.fake

import FakeLocalDataSource
import com.digitalarchitects.rmc_app.data.auth.AuthResult
import com.digitalarchitects.rmc_app.data.auth.SignUpRequest
import com.digitalarchitects.rmc_app.data.mapper.toUser
import com.digitalarchitects.rmc_app.data.mapper.toUserListFromLocal
import com.digitalarchitects.rmc_app.data.remote.dto.user.SignupDTO
import com.digitalarchitects.rmc_app.data.remote.dto.user.UpdateUserDTO
import com.digitalarchitects.rmc_app.domain.model.User
import com.digitalarchitects.rmc_app.domain.repo.UserRepository

// Class inherits from userRepository interface overrides the getUsers() fun to return fake data.
class FakeUserRepository : UserRepository {
    override suspend fun signUp(request: SignUpRequest): AuthResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun signIn(username: String, password: String): AuthResult<Unit> {
        return if (username == "validUser" && password == "validPassword") {
            AuthResult.Authorized(Unit)
        } else {
            AuthResult.Unauthorized()
        }    }

    override suspend fun authenticate(): AuthResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllUsers(): List<User> {
        return FakeLocalDataSource.userList.toUserListFromLocal()
    }

    override suspend fun getAllUsersFromLocalCache(): List<User> {
        return FakeLocalDataSource.userList.toUserListFromLocal()
    }

    override suspend fun getAllUsersFromRemote() {
    }

    override suspend fun getUserById(userId: String): User {
        return FakeLocalDataSource.userList.first { it.userId == userId }.toUser()
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
        return "1"
    }
}