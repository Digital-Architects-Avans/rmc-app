![rmc-logo](https://github.com/Digital-Architects-Avans/rmc-api/raw/master/src/main/resources/images/rmc-logo.png)

# Rent My Car - Android App

Welcome to the Vehicle Rental App, a modern Android application developed using Jetpack Compose and Kotlin. This app provides a seamless experience for users to register, rent vehicles, and manage their rental activities. It follows the principles of Modern Android Development, incorporating the latest technologies and recommended architecture.


## Table of Contents
- [Modern Android Development](https://github.com/Digital-Architects-Avans/rmc-app/edit/master/README.md#modern-android-development)
- [Features](https://github.com/Digital-Architects-Avans/rmc-app/edit/master/README.md#features)
- [Getting started](https://github.com/Digital-Architects-Avans/rmc-app/edit/master/README.md#getting-started)
- [Contributing](https://github.com/Digital-Architects-Avans/rmc-app/edit/master/README.md#contributing)


## Modern Android Development
The app is built following the principles of Modern Android Development, adhering to the recommended architecture. Key features include:
- Reactive and layered architecture
- Unidirectional Data Flow (UDF) in all layers
- UI layer with state holders for a clean and manageable UI
Integration of Coroutines and flows for asynchronous operations
- Dependency injection using Dagger Hilt for scalability
- MVVM design pattern with Presentation, Domain, and Data layers
- Implementation of Room for local data storage and Retrofit for remote data retrieval
- Jetpack Compose for building modern and intuitive user interfaces


## Features
User Authentication
- Users can register an account and log in securely.

Vehicle Registration
- Vehicle owners can register their vehicles for rental.

Interactive Map
- Utilizes the Google Android Map API to display all available vehicles for rent on an interactive map.

Vehicle Rental
- Users can browse and rent available vehicles for their transportation needs.

Rental Management
- Vehicle owners can manage rental requests, accepting or denying requests from users.

Account Details
- Users can edit and update their account details.
  

## Getting Started
To run the app locally, follow these steps:
1. Clone the repository:

```bash
git clone https://github.com/your-username/vehicle-rental-app.git
```
2. Make sure you have an instance of the [RMC API 2](https://github.com/Digital-Architects-Avans/rmc-api-2) running locally.
   The Android APP uses this API as the backend and will connect with the API running on localhost (10.0.0.2/8080 or 10.0.0.2:8443)

2. Open the project in Android Studio.

3. Build and run the app on an emulator or physical device.


## Contributing
Feel free to contribute to the development of this app by submitting bug reports, feature requests, or pull requests. Together, let's make the Rent My Car App even better!
