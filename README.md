# Android App Architecture #2

## Content

### Components
- **MainScreen** is the mother component of MainActivity.kt
- **LongBasicDropdownMenu** contains an ExtendedFloatingActionButton with DropdownMenu Items that exposes an callback function
- **LoadingComponent** contains a CircularProgressIndicator that spins when rendered 
- **ResultComponent** contains a LazyVerticalStaggeredGrid which renders VehicleCard composables displaying info about vehicles

### API
Fetches vehicle data from [NHTSA](https://vpic.nhtsa.dot.gov/api/)

Endpoint used in this example: [Get Models for Make](https://vpic.nhtsa.dot.gov/api/vehicles/getmodelsformake/honda?format=json)

### HTTP Client
This example uses the [Retrofit](https://square.github.io/retrofit/) library to make HTTP requests

### Dependency Injection
This app utilizes [Hilt](https://dagger.dev/hilt/) for providing the necessary dependencies.

Check out [Android with Hilt docs](https://developer.android.com/training/dependency-injection/hilt-android) on Android Developer for more tips and tricks.


### Overview of app
![overview of app architecture](https://github.com/user-attachments/assets/e7c00bbe-94cf-4301-a007-1efef08ffa31)


## TODO
- Implement the VehicleViewModel containing a UiState for Vehicles
- Use VehicleViewModel to fetch data from VehicleRepository
- Handle response from repository, and manage the UiState
- Show the state of result, isLoading and isError in MainScreen if they are available in the VehicleViewModel
