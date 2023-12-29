package com.digitalarchitects.rmc_app.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate

@Database(
    version = 1,
    entities = [LocalUser::class, LocalVehicle::class, LocalRental::class],
    exportSchema = false
)
abstract class RmcRoomDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val vehicleDao: VehicleDao
    abstract val rentalDao: RentalDao
}

class Converters {
    @TypeConverter
    fun fromLocalDate(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it) }
    }

    @TypeConverter
    fun toLocalDate(date: LocalDate?): String? {
        return date?.toString()
    }
}
