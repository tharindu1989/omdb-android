# Movie Search Android App

## Overview

This Android app is designed to search for movies using the OMDB API. It follows the principles of Clean Architecture, separating concerns into distinct layers: `data` for data-related operations, `domain` for use cases, and `presentation` for UI.

## Project Structure

The project is structured into the following packages:

- **data:** This package contains classes responsible for data-related operations, including API calls and data source implementations.

- **domain:** The domain layer contains use case classes that define the business logic of the application.

- **presentation:** This package focuses on the user interface (UI) aspects of the app, including activities, fragments, and view models.

## Dependencies

This project utilizes the following key dependencies:

- **Dagger-HILT:** For efficient and easy-to-maintain dependency injection.

- **Retrofit:** A type-safe HTTP client for making API calls to the OMDB service.

## Getting Started

### Prerequisites

- Make sure you have Android Studio installed.

### Setup

1. Clone the repository:
   `git clone https://github.com/your-username/movie-ratings-app.git`
2. Open the project in Android Studio.
3. Build and run the app on your preferred emulator or Android device.

### Configuration
##### OMDB API Key
To make API requests to the OMDB service, you need to obtain an API key. If you don't have one, [click here](https://www.omdbapi.com/apikey.aspx) to obtain your OMDB API key. Once you have your API key, update the `ApiKeyInterceptor` class in the `data` package with your unique key.

```kotlin
// Add your OMDB API key here
private const val API_KEY = "YOUR_OMDB_API_KEY" 
```
### Screens
Movie Screen               |  Details Screen
:-------------------------:|:-------------------------:
![](/screens/movie-screen.png)  |  ![](/screens/details-screen.png)

### License

This project is licensed under the [MIT License](LICENSE).

### Acknowledgments
Thanks for the OMDB API for providing their APIs.