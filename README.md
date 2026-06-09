# Vaniga - Product Discovery App

Vaniga is a modern Android application built with **Kotlin** and **Jetpack Compose**. It demonstrates a robust implementation of **Clean Architecture**, fetching real-time e-commerce data from the Platzi Fake Store API.

## 🚀 Features

- **Product Listing**: Displays a high-performance list of products using `LazyColumn` and infinite scroller with pagination using `paging 3 library`.
- **Category Filtering**: Displays a product based on filter users select.
- **Product Details**: A dedicated detail view for each product featuring a full description and pricing.
- **Image Carousel**: Horizontally swiping image gallery on the detail page using `HorizontalPager`.
- **Shimmer Loading**: Professional skeleton loading (Shimmer effect) for a better user experience during data fetching.
- **Robust Error Handling**: Handles offline states and API errors with a "Try Again" mechanism.
- **Theme Support**: Full support for **Light and Dark Mode** using Material 3 semantic color tokens.
- **Clean Architecture**: Strictly follows the separation of concerns (Data, Domain, and Presentation layers).

---

## 🛠 Tech Stack

- **UI Framework**: Jetpack Compose (100% Declarative UI)
- **Architecture**: Clean Architecture + MVVM
- **Dependency Injection**: Hilt (Dagger)
- **Networking**: Retrofit 2 + OkHttp + Gson
- **Image Loading**: Coil
- **Concurrency**: Kotlin Coroutines & Flow
- **State Management**: Compose State & ViewModel
- **Testing**: JUnit 4, MockK

---

## 🏗 Architecture Overview

The project is divided into three main layers to ensure testability, scalability, and maintainability:

### 1. Domain Layer
The core of the application. It contains:
- **Entities**: Clean data models (`Product`).
- **Use Cases**: Encapsulated business logic (`GetProductsUseCase`).
- **Repository Interfaces**: Definitions for data operations.

### 2. Data Layer
The implementation of data retrieval:
- **DTOs**: Data Transfer Objects for API responses.
- **Mappers**: Converts DTOs into Domain Entities.
- **Repository Implementation**: Logic to handle network calls and exception handling (IOException/HttpException).

### 3. Presentation Layer (MVVM)
- **ViewModels**: Manages UI state using `StateFlow` and handles user interactions.
- **Screens**: Composable functions that observe the state and render the UI.
- **Components**: Reusable UI elements like Product Cards and Shimmer effects.

---

## 🧪 Testing Strategy

The project includes a comprehensive suite of tests:

- **Unit Tests (`src/test`)**: 
    - **Mappers**: Verifies that API data is correctly cleaned and transformed.
    - **ViewModels**: Tests the UI state flow (Loading -> Success/Error).

---

## 📸 App Screenshots

| Product List (Light) | Product List (Dark) | Product Detail (Light) | Product Detail (Dark) |
|-------|-------|-------|-------|
| <img width="320" height="714" alt="image" src="https://github.com/user-attachments/assets/a9653a2a-f311-4303-ad55-31b2d5103c0f" /> | <img width="320" height="714" alt="image" src="https://github.com/user-attachments/assets/8e085b0c-3644-47a1-8ca7-3f4d4ff53ea9" /> | <img width="320" height="714" alt="PDP (Light)" src="https://github.com/user-attachments/assets/3cc4753b-e865-40f3-b785-8867235db215" /> | <img width="320" height="714" alt="PDP (Dark)" src="https://github.com/user-attachments/assets/ea912525-750c-4818-b908-ff09b159c2c2" /> |


---

## 📸 Expection handling Screenshots

| No Internet Issue | Server Error Issue |
|-------|-------|
| <img width="213.33" height="476" alt="IO Expection" src="https://github.com/user-attachments/assets/ffab42c2-cdca-4a2a-b2cd-759e72145a65" />| <img width="213.33" height="476" alt="HTTP Expection" src="https://github.com/user-attachments/assets/fd49a7fa-359e-4368-b15f-b77a9a5bb219" />|

## ⚙️ Setup & Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/vaniga.git
   ```
2. Open the project in **Android Studio (Ladybug or newer)**.
3. Ensure the JDK version is set to **17 or 21**.
4. Build and run the app on an emulator or physical device.

---

## 🔗 API Reference
This app consumes the following API:
`GET https://api.escuelajs.co/api/v1/products`

---

## 📝 Author
**Praveen Kumar V**
