package com.digitalarchitects.rmc_app.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserTable::class, /*VehicleTable::class, RentalTable::class*/],
    version= 1
)
abstract class RmcRoomDatabase:RoomDatabase() {

    abstract val userDao: UserDao
//    abstract val vehicleDao: VehicleDao
//    abstract val rentalDao: RentalDao

}