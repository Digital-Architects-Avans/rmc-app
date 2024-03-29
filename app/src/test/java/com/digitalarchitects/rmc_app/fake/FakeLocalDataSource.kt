import com.digitalarchitects.rmc_app.data.local.LocalRental
import com.digitalarchitects.rmc_app.data.local.LocalUser
import com.digitalarchitects.rmc_app.data.local.LocalVehicle
import com.digitalarchitects.rmc_app.domain.model.EngineType
import com.digitalarchitects.rmc_app.domain.model.RentalStatus
import com.digitalarchitects.rmc_app.domain.model.UserType
import kotlinx.datetime.LocalDate

object FakeLocalDataSource {
    val userList = listOf(
        LocalUser(
            userId = "1",
            email = "email",
            password = "password",
            salt = "salt",
            userType = UserType.STAFF,
            firstName = "firstName",
            lastName = "lastName",
            phone = "telephone",
            street = "street",
            buildingNumber = "buildingNumber",
            zipCode = "zipCode",
            city = "city",
            profileImageSrc = "src"
        ),
        LocalUser(
            userId = "2",
            email = "email2",
            password = "password",
            salt = "salt",
            userType = UserType.CLIENT,
            firstName = "firstName2",
            lastName = "lastName2",
            phone = "telephone2",
            street = "street2",
            buildingNumber = "buildingNumber2",
            zipCode = "zipCode2",
            city = "city2",
            profileImageSrc = "src2"
        )
    )
    val vehicleList = listOf(
        LocalVehicle(
            vehicleId = "1",
            userId = "1",
            brand = "brand",
            model = "model",
            year = 1,
            vehicleClass = "vehicleClass1",
            engineType = EngineType.BEV,
            licensePlate = "licensePlate1",
            imgLink = 1,
            latitude = 2.0f,
            longitude = 2.0f,
            price = 2.0,
            availability = true,
            description = "placeholder",
            address = "City"
        ),
        LocalVehicle(
            vehicleId = "2",
            userId = "2",
            brand = "brand2",
            model = "model2",
            year = 2,
            vehicleClass = "vehicleClass2",
            engineType = EngineType.BEV,
            licensePlate = "licensePlate2",
            imgLink = 2,
            latitude = 2.0f,
            longitude = 2.0f,
            price = 2.0,
            availability = true,
            description = "placeholder",
            address = "City"
        )
    )
    val rentalList = listOf(
        LocalRental(
            rentalId = "2",
            vehicleId = "2",
            userId = "1",
            date = LocalDate(2021, 1, 1),
            price = 1.0,
            latitude = 1.0f,
            longitude = 1.0f,
            status = RentalStatus.PENDING,
            distanceTravelled = 1.0,
            score = 1
        ),
        LocalRental(
            rentalId = "2",
            vehicleId = "2",
            userId = "2",
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