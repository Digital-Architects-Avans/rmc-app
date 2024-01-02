package com.digitalarchitects.rmc_app.room

interface RmcRoomDatabaseRepo {

    // User queries on the local Room database
    suspend fun getAllUsersFromLocalDb(): List<LocalUser>
    suspend fun getUserByIdFromLocalDb(userId: Int): LocalUser
    suspend fun addAllUsersToLocalDb(users: List<LocalUser>)
    suspend fun addUserToLocalDb(user: LocalUser): Long
    suspend fun updateUserInLocalDb(user: LocalUser)
    suspend fun deleteUserFromLocalDb(user: LocalUser)
    suspend fun getFirstName(userId: Int): String?
    suspend fun clearUserCache()

    // Vehicle queries on the local Room database
    suspend fun getAllVehiclesFromLocalDb(): List<LocalVehicle>
    suspend fun getVehicleByIdFromLocalDb(vehicleId: Int): LocalVehicle
    suspend fun addAllVehiclesToLocalDb(vehicles: List<LocalVehicle>)
    suspend fun addVehicleToLocalDb(vehicle: LocalVehicle): Long
    suspend fun updateVehicleInLocalDb(vehicle: LocalVehicle)
    suspend fun deleteVehicleFromLocalDb(vehicle: LocalVehicle)
    suspend fun getVehicleModel(vehicleId: Int): String?
    suspend fun clearVehicleCache()


    // Rental queries on the local Room database
    suspend fun getAllRentalsFromLocalDb(): List<LocalRental>
    suspend fun getRentalByIdFromLocalDb(rentalId: Int): LocalRental
    suspend fun getRentalsForUserFromLocalDb(userId: Int): List<LocalRental>?
    suspend fun getRentalsForVehicleFromLocalDb(vehicleId: Int): List<LocalRental>?
    suspend fun addAllRentalsToLocalDb(rentals: List<LocalRental>)
    suspend fun addRentalToLocalDb(rental: LocalRental): Long
    suspend fun updateRentalInLocalDb(rental: LocalRental)
    suspend fun deleteRentalFromLocalDb(rental: LocalRental)
    suspend fun getRentalDate(rentalId: Int): String?
    suspend fun clearRentalCache()
}