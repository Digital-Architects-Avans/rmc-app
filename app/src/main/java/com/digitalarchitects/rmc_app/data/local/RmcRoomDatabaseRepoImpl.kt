package com.digitalarchitects.rmc_app.data.local

class RmcRoomDatabaseRepoImpl(
    private val userDao: UserDao,
    private val vehicleDao: VehicleDao,
    private val rentalDao: RentalDao
) : RmcRoomDatabaseRepo {
    override suspend fun getAllUsersFromLocalDb(): List<LocalUser> =
        userDao.getAllUsers()

    override suspend fun getUserByIdFromLocalDb(userId: String): LocalUser =
        userDao.getUserById(userId)

    override suspend fun addAllUsersToLocalDb(users: List<LocalUser>) =
        userDao.addAllUsers(users)

    override suspend fun addUserToLocalDb(user: LocalUser) =
        userDao.addUser(user)

    override suspend fun updateUserInLocalDb(user: LocalUser) =
        userDao.upsertUser(user)

    override suspend fun deleteUserFromLocalDb(user: LocalUser) =
        userDao.deleteUser(user)

    override suspend fun getFirstName(userId: String): String? =
        userDao.getFirstName(userId)

    override suspend fun clearUserCache() =
        userDao.clearCache()

    // --------------------------------------------------------------------------------------

    override suspend fun getAllVehiclesFromLocalDb(): List<LocalVehicle> =
        vehicleDao.getAllVehicles()

    override suspend fun getVehicleByIdFromLocalDb(vehicleId: String): LocalVehicle =
        vehicleDao.getVehicleById(vehicleId)

    override suspend fun getVehiclesForUserFromLocalDb(userId: String): List<LocalVehicle>? =
        vehicleDao.getVehiclesForUser(userId)

    override suspend fun addAllVehiclesToLocalDb(vehicles: List<LocalVehicle>) =
        vehicleDao.addAllVehicles(vehicles)

    override suspend fun addVehicleToLocalDb(vehicle: LocalVehicle) =
        vehicleDao.insertVehicle(vehicle)

    override suspend fun updateVehicleInLocalDb(vehicle: LocalVehicle) =
        vehicleDao.upsertVehicle(vehicle)

    override suspend fun deleteVehicleFromLocalDb(vehicle: LocalVehicle) =
        vehicleDao.deleteVehicle(vehicle)

    override suspend fun getVehicleModel(vehicleId: String) =
        vehicleDao.getVehicleModel(vehicleId)

    override suspend fun clearVehicleCache() =
        vehicleDao.clearCache()

    // --------------------------------------------------------------------------------------

    override suspend fun getAllRentalsFromLocalDb(): List<LocalRental> =
        rentalDao.getAllRentals()

    override suspend fun getRentalByIdFromLocalDb(rentalId: String): LocalRental =
        rentalDao.getRentalById(rentalId)

    override suspend fun getRentalsForRenterFromLocalDb(userId: String): List<LocalRental>? =
        rentalDao.getRentalsForRenter(userId)

    override suspend fun getRentalsForVehicleFromLocalDb(vehicleId: String): List<LocalRental>? =
        rentalDao.getRentalsForVehicle(vehicleId)

    override suspend fun getRentalDetailsFromLocalDb(rentalId: String): Triple<LocalRental, LocalVehicle, LocalUser> {
        val rental = rentalDao.getRentalById(rentalId)
        val vehicle = vehicleDao.getVehicleById(rental.vehicleId)
        val user = userDao.getUserById(rental.userId)
        return Triple(rental, vehicle, user)
    }

    override suspend fun addAllRentalsToLocalDb(rentals: List<LocalRental>) =
        rentalDao.addAllRentals(rentals)

    override suspend fun addRentalToLocalDb(rental: LocalRental) =
        rentalDao.addRental(rental)

    override suspend fun updateRentalInLocalDb(rental: LocalRental) =
        rentalDao.upsertRental(rental)

    override suspend fun deleteRentalFromLocalDb(rental: LocalRental) =
        rentalDao.deleteRental(rental)

    override suspend fun getRentalDate(rentalId: String): String? =
        rentalDao.getRentalDate(rentalId)

    override suspend fun clearRentalCache() =
        rentalDao.clearCache()
}