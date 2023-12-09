package com.digitalarchitects.rmc_app.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserTable::class],
    version= 1
)
abstract class UserDatabase:RoomDatabase() {

    abstract val dao:UserDao
}