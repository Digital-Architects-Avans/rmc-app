package com.digitalarchitects.rmc_app.fake

import FakeLocalDataSource
import com.digitalarchitects.rmc_app.data.mapper.toUser
import com.digitalarchitects.rmc_app.data.mapper.toUserListFromLocal
import com.digitalarchitects.rmc_app.domain.repo.UserRepository
import com.digitalarchitects.rmc_app.model.User

// Class inherits from userRepository interface overrides the getUsers() fun to return fake data.
class FakeUserRepository : UserRepository {

    override suspend fun getAllUsers(): List<User> {
        return FakeLocalDataSource.userList.toUserListFromLocal()
    }

    override suspend fun getAllUsersFromLocalCache(): List<User> {
        return FakeLocalDataSource.userList.toUserListFromLocal()
    }

    override suspend fun getAllUsersFromRemote() {
    }

    override suspend fun getUserById(userId: Int): User {
        return FakeLocalDataSource.userList.first { it.id == userId }.toUser()
    }

    override suspend fun addUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(user: User): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getFirstName(userId: Int): String? {
        TODO("Not yet implemented")
    }
}