package com.digitalarchitects.rmc_app.data.local

interface RmcRoomDatabaseRepo {

    // User queries on the local Room database
    suspend fun getAllUsersFromLocalDb(): List<LocalUser>
    suspend fun getUserByIdFromLocalDb(userId: String): LocalUser
    suspend fun addAllUsersToLocalDb(users: List<LocalUser>)
    suspend fun addUserToLocalDb(user: LocalUser): Long
    suspend fun updateUserInLocalDb(user: LocalUser)
    suspend fun deleteUserFromLocalDb(user: LocalUser)
    suspend fun getFirstName(userId: String): String?
    suspend fun clearUserCache()

    // Vehicle queries on the local Room database
    suspend fun getAllVehiclesFromLocalDb(): List<LocalVehicle>
    suspend fun getVehicleByIdFromLocalDb(vehicleId: String): LocalVehicle
    suspend fun getVehiclesForUserFromLocalDb(userId: String): List<LocalVehicle>?
    suspend fun addAllVehiclesToLocalDb(vehicles: List<LocalVehicle>)
    suspend fun addVehicleToLocalDb(vehicle: LocalVehicle): Long
    suspend fun updateVehicleInLocalDb(vehicle: LocalVehicle)
    suspend fun deleteVehicleFromLocalDb(vehicle: LocalVehicle)
    suspend fun getVehicleModel(vehicleId: String): String?
    suspend fun clearVehicleCache()


    // Rental queries on the local Room database
    suspend fun getAllRentalsFromLocalDb(): List<LocalRental>
    suspend fun getRentalByIdFromLocalDb(rentalId: String): LocalRental
    suspend fun getRentalsForRenterFromLocalDb(userId: String): List<LocalRental>?
    suspend fun getRentalsForVehicleFromLocalDb(vehicleId: String): List<LocalRental>?
    suspend fun getRentalDetailsFromLocalDb(rentalId: String): Triple<LocalRental, LocalVehicle, LocalUser>
    suspend fun addAllRentalsToLocalDb(rentals: List<LocalRental>)
    suspend fun addRentalToLocalDb(rental: LocalRental): Long
    suspend fun updateRentalInLocalDb(rental: LocalRental)
    suspend fun deleteRentalFromLocalDb(rental: LocalRental)
    suspend fun getRentalDate(rentalId: String): String?
    suspend fun clearRentalCache()
}