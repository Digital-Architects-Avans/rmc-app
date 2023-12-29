
import com.digitalarchitects.rmc_app.model.EngineType
import com.digitalarchitects.rmc_app.model.RentalStatus
import com.digitalarchitects.rmc_app.model.UserType
import com.digitalarchitects.rmc_app.room.LocalRental
import com.digitalarchitects.rmc_app.room.LocalUser
import com.digitalarchitects.rmc_app.room.LocalVehicle
import kotlinx.datetime.LocalDate

object FakeLocalDataSource {
    val userList = listOf(
        LocalUser(
            id = 1,
            email = "email",
            userType = UserType.STAFF,
            firstName = "firstName",
            lastName = "lastName",
            phone = "telephone",
            street = "street",
            buildingNumber = "buildingNumber",
            zipCode = "zipCode",
            city = "city"
        ),
        LocalUser(
            id = 2,
            email = "email2",
            userType = UserType.CLIENT,
            firstName = "firstName2",
            lastName = "lastName2",
            phone = "telephone2",
            street = "street2",
            buildingNumber = "buildingNumber2",
            zipCode = "zipCode2",
            city = "city2"
        )
    )
    val vehicleList = listOf(
        LocalVehicle(
            id = 1,
            userId = 1,
            brand = "brand",
            model = "model",
            year = 1,
            vehicleClass = "vehicleClass1",
            engineType = EngineType.BEV,
            licensePlate = "licensePlate1",
            imgLink = "https://www.google.com",
            latitude = 2.0f,
            longitude = 2.0f,
            price = 2.0,
            availability = true
        ),
        LocalVehicle(
            id = 2,
            userId = 2,
            brand = "brand2",
            model = "model2",
            year = 2,
            vehicleClass = "vehicleClass2",
            engineType = EngineType.BEV,
            licensePlate = "licensePlate2",
            imgLink = "https://www.google.com",
            latitude = 2.0f,
            longitude = 2.0f,
            price = 2.0,
            availability = true
        )
    )
    val rentalList = listOf(
        LocalRental(
            id = 1,
            vehicleId = 1,
            userId = 1,
            date = LocalDate(2021, 1, 1),
            price = 1.0,
            latitude = 1.0f,
            longitude = 1.0f,
            status = RentalStatus.PENDING,
            distanceTravelled = 1.0,
            score = 1
        ),
        LocalRental(
            id = 2,
            vehicleId = 2,
            userId = 2,
            date = LocalDate(2021, 2, 2),
            price = 2.0,
            latitude = 2.0f,
            longitude = 2.0f,
            status = RentalStatus.APPROVED,
            distanceTravelled = 2.0,
            score = 2
        )
    )
}