package com.digitalarchitects.rmc_app
import com.digitalarchitects.rmc_app.data.local.LocalRental
import com.digitalarchitects.rmc_app.data.local.LocalUser
import com.digitalarchitects.rmc_app.data.local.LocalVehicle
import com.digitalarchitects.rmc_app.data.mapper.toLocalRentalListFromRemote
import com.digitalarchitects.rmc_app.data.mapper.toRental
import com.digitalarchitects.rmc_app.data.mapper.toRentalListFromLocal
import com.digitalarchitects.rmc_app.data.mapper.toTripleFromLocal
import com.digitalarchitects.rmc_app.data.mapper.toUser
import com.digitalarchitects.rmc_app.data.mapper.toVehicle
import com.digitalarchitects.rmc_app.data.remote.dto.rental.RemoteRental
import com.digitalarchitects.rmc_app.domain.model.EngineType
import com.digitalarchitects.rmc_app.domain.model.Rental
import com.digitalarchitects.rmc_app.domain.model.RentalStatus
import com.digitalarchitects.rmc_app.domain.model.UserType
import junit.framework.TestCase.assertEquals
import kotlinx.datetime.LocalDate
import org.junit.Test

class MapperTests {

    @Test
    fun localRental_to_Rental_MapsCorrectly() {
        val testDate = LocalDate.parse("2023-01-01")

        val localRentalList = listOf(
            LocalRental(
                rentalId = "1",
                vehicleId = "2",
                userId = "3",
                date = testDate,
                price = 50.0,
                latitude = 1.0f,
                longitude = 2.0f,
                status = RentalStatus.PENDING,
                distanceTravelled = 100.0,
                score = 4
            ),
        )

        val result = localRentalList.toRentalListFromLocal()

        assertEquals(localRentalList.size, result.size)

        val expectedRental = Rental(
            rentalId = "1",
            vehicleId = "2",
            userId = "3",
            date = testDate,
            price = 50.0,
            latitude = 1.0f,
            longitude = 2.0f,
            status = RentalStatus.PENDING,
            distanceTravelled = 100.0,
            score = 4
        )
        assertEquals(expectedRental, result.first())

    }

    @Test
    fun listRemoteRental_to_LocalRentalListFromRemote_MapsCorrectly() {
        val testDate = LocalDate.parse("2023-01-01")
        val remoteRentalList = listOf(
            RemoteRental(
                rentalId = "1",
                vehicleId = "2",
                userId = "3",
                date = testDate,
                price = 50.0,
                latitude = 1.0f,
                longitude = 2.0f,
                status = RentalStatus.PENDING,
                distanceTravelled = 200.0,
                score = 4,
                _id = "5"
            )
        )

        val result = remoteRentalList.toLocalRentalListFromRemote()

        assertEquals(remoteRentalList.size, result.size)

        val expectedLocalRental = LocalRental(
            rentalId = "1",
            vehicleId = "2",
            userId = "3",
            date = testDate,
            price = 50.0,
            latitude = 1.0f,
            longitude = 2.0f,
            status = RentalStatus.PENDING,
            distanceTravelled = 200.0,
            score = 4
        )
        assertEquals(expectedLocalRental.rentalId, result.first().rentalId)
        assertEquals(expectedLocalRental.vehicleId, result.first().vehicleId)
        assertEquals(expectedLocalRental.userId, result.first().userId)
        assertEquals(expectedLocalRental.date, result.first().date)
        assertEquals(expectedLocalRental.price, result.first().price)
        assertEquals(expectedLocalRental.latitude, result.first().latitude)
        assertEquals(expectedLocalRental.longitude, result.first().longitude)
        assertEquals(expectedLocalRental.status, result.first().status)
        assertEquals(expectedLocalRental.distanceTravelled, result.first().distanceTravelled)
        assertEquals(expectedLocalRental.score, result.first().score)
    }

    @Test
    fun localRental_Vehicle_User_toTriple_MapsCorrectly() {
        val testDate = LocalDate.parse("2023-01-01")
        val localRental = LocalRental(
            rentalId = "1",
            vehicleId = "2",
            userId = "3",
            date = testDate,
            price = 50.0,
            latitude = 1.0f,
            longitude = 2.0f,
            status = RentalStatus.PENDING,
            distanceTravelled = 100.0,
            score = 4
        )

        val localVehicle = LocalVehicle(
            vehicleId = "2",
            userId = "3",
            brand = "Brand",
            model = "Model",
            year = 2022,
            vehicleClass = "Sedan",
            engineType = EngineType.BEV,
            licensePlate = "ABC",
            imgLink = 1,
            description = "Description",
            address = "breda",
            latitude = 3.0f,
            longitude = 4.0f,
            price = 60.0,
            availability = true
        )

        val localUser = LocalUser(
            userId = "3",
            email = "testmail",
            password = "password",
            salt = "salt",
            userType = UserType.CLIENT,
            firstName = "naam",
            lastName = "naam2",
            phone = "06",
            street = "bredalaan",
            buildingNumber = "123",
            zipCode = "3400kd",
            city = "Breda",
            profileImageSrc = "src"
        )

        val triple = Triple(localRental, localVehicle, localUser)

        val result = triple.toTripleFromLocal()

        assertEquals(localRental.toRental(), result.first)
        assertEquals(localVehicle.toVehicle(), result.second)
        assertEquals(localUser.toUser(), result.third)
    }

}
