# ConsultAssist - Patient Health Data Collection System

A simplified patient health data collection system where patients can enter their health information before appointments, allowing doctors to have all necessary details beforehand.

## Tech Stack
- **Backend**: Java 17 with Spring Boot
- **Frontend**: React with Tailwind CSS
- **Database**: MySQL
- **Authentication**: JWT

## Features
- Patient registration and login
- Doctor registration and login
- Patient health data submission (symptoms, medical history, age)
- Doctor dashboard to view patient data
- Secure JWT-based authentication
- CORS-enabled for cross-origin requests
- Centralized API client with automatic token management

## Prerequisites
- Java 17
- MySQL
- Node.js (for frontend)
- Maven

## Setup Instructions

### Backend Setup

1. Create a MySQL database:
   ```sql
   CREATE DATABASE consultassist;
   ```

2. Update database credentials in `src/main/resources/application.properties` if needed:
   ```
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. Build and run the backend:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

   The backend will start on port 8082.

### Frontend Setup

1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the frontend:
   ```bash
   npm start
   ```

   The frontend will start on port 3000.

## Demo Credentials

- **Patient**: 
  - Username: patient1
  - Password: password123

- **Doctor**:
  - Username: doctor1
  - Password: password123

## API Endpoints

- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration
- `POST /api/patient/data` - Submit patient health data
- `GET /api/doctor/patients` - Get all patient data (doctor only)

## Project Structure

### Backend
```
src/main/java/com/consultassist/
├── ConsultAssistApplication.java
├── config/
│   ├── SecurityConfig.java
│   └── DataInitializer.java
├── controller/
│   ├── AuthController.java
│   └── PatientDataController.java
├── dto/
│   ├── LoginRequest.java
│   ├── LoginResponse.java
│   ├── PatientDataRequest.java
│   └── PatientDataResponse.java
├── entity/
│   ├── User.java
│   ├── Role.java
│   └── PatientData.java
├── repository/
│   ├── UserRepository.java
│   └── PatientDataRepository.java
├── security/
│   └── JwtUtil.java
└── service/
    ├── UserService.java
    └── PatientDataService.java
```

### Frontend
```
src/
├── App.js
├── index.js
├── components/
│   ├── Navbar.js
│   ├── Login.js
│   ├── PatientDashboard.js
│   └── DoctorDashboard.js
├── config/
│   ├── api.js
│   └── axios.js
└── styles/
    ├── index.css
    └── App.css
```


