package com.digitalarchitects.rmc_app.room
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UserDao {

    @Query("SELECT * FROM LocalUser ORDER BY userId ASC")
    suspend fun getAllUsers(): List<LocalUser>

    @Query("SELECT * FROM LocalUser WHERE userId = :userId")
    suspend fun getUserById(userId: Int): LocalUser

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllUsers(users: List<LocalUser>)

    @Query("SELECT firstName FROM LocalUser WHERE userId = :userId")
    suspend fun getFirstName(userId: Int): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: LocalUser): Long

    @Upsert
    fun upsertUser(user: LocalUser)

    @Delete
    fun deleteUser(user: LocalUser)

    @Query("DELETE FROM LocalUser")
    suspend fun clearCache()

//    TODO("GET CURRENT USER")
//    @Query("SELECT * FROM userentity LIMIT 1")
//    fun getUser(): UserTable?
}