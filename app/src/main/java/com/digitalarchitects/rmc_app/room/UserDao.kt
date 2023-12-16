package com.digitalarchitects.rmc_app.room
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.digitalarchitects.rmc_app.data.editmyaccount.EditMyAccountUIEvent
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM usertable ORDER BY firstName ASC")
    fun getUsersOrderedByFirstName(): Flow<List<UserTable>>

    // TODO GET CURRENT USER
//    @Query("SELECT * FROM usertable LIMIT 1")
//    fun getUser(): UserTable?

    @Query("SELECT * FROM usertable WHERE id = 1")
    fun getUserById(): UserTable

    @Query("SELECT firstName FROM usertable LIMIT 1")
    fun getFirstName(): String?

    @Upsert
    suspend fun upsertUser(user: UserTable)

    @Insert
    suspend fun insertUser(user:UserTable)

    @Delete
    suspend fun deleteUser(user:UserTable)
}