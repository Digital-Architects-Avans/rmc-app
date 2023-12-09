package com.digitalarchitects.rmc_app.room
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM usertable ORDER BY firstName ASC")
    fun getUsersOrderedByFirstName(): Flow<List<UserTable>>

    @Query("SELECT id = 1 FROM usertable")
    fun getUser()

    @Upsert
    suspend fun upsertUser(user:UserTable)

    @Insert
    suspend fun insertUser(user:UserTable)

    @Delete
    suspend fun deleteUser()
}