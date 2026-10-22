RideWise — Modular Ride-Sharing System
RideWise is a clean, modular, low-level designed (LLD) ride-sharing console application built from scratch in pure Java (Java 21). It strictly follows SOLID principles, core OOP concepts, and design patterns like the Strategy Pattern to ensure high maintainability, clean separation of concerns, and full extensibility without relying on heavy frameworks.
---
📋 Table of Contents
Architecture & Design Principles
Design Patterns Used
Project Structure
Domain Entities
Key Features
Prerequisites
Getting Started
Usage Guide (Console Menu)
Future Extensibility
---
🏗️ Architecture & Design Principles
This project is built using plain Java SE with manual dependency injection, putting core software engineering principles into practice:
SOLID Principles
Single Responsibility Principle (SRP): Each class has a single, well-defined job.
`RiderService`: Handles rider registrations and lookups.
`DriverService`: Manages driver profiles, location updates, and availability.
`RideService`: Orchestrates the ride lifecycle, driver matching, and fare calculations.
Open/Closed Principle (OCP): New matching algorithms or fare calculation rules can be added easily by implementing their respective interfaces without touching existing service logic.
Liskov Substitution Principle (LSP): Any strategy implementation can be swapped seamlessly wherever `RideMatchingStrategy` or `FareStrategy` is expected.
Interface Segregation Principle (ISP): Interfaces are kept lean and specific so that classes only depend on methods they actually use.
Dependency Inversion Principle (DIP): High-level services depend on abstractions (interfaces) rather than concrete implementations.
Core OOP Principles
DRY (Don't Repeat Yourself): Reusable business logic for state transitions and lookups.
KISS (Keep It Simple, Stupid): Straightforward, readable domain modeling.
Composition over Inheritance: Behavior is plugged into services via constructors rather than deep inheritance hierarchies.
---
🛠️ Design Patterns Used
Strategy Pattern: Decouples algorithms for driver selection (`RideMatchingStrategy`) and pricing computation (`FareStrategy`).
Manual Dependency Injection: Services and strategies are explicitly wired together in the entry point (`Main.java`), keeping the app lightweight and framework-agnostic.
---
📁 Project Structure
```text
RideWiseModularRideSharingSystem/
│
├── src/main/java/com/airtribe/rideSharingSystem/
│   ├── constants/
│   │   ├── RideStatus.java       # REQUESTED, ASSIGNED, COMPLETED, CANCELLED
│   │   └── VehicleType.java      # BIKE, AUTO, CAR
│   │
│   ├── model/
│   │   ├── Driver.java           # Driver details, location, and availability flag
│   │   ├── FareReceipt.java      # Receipt containing ride cost and timestamp
│   │   ├── Ride.java             # Core ride lifecycle model
│   │   └── Rider.java            # Rider profile information
│   │
│   ├── service/
│   │   ├── DriverService.java    # Driver management logic
│   │   ├── RideService.java      # Ride request, completion, and cancellation workflow
│   │   └── RiderService.java     # Rider registration logic
│   │
│   ├── strategy/
│   │   ├── FareStrategy.java             # Interface for fare rules
│   │   ├── DefaultFareStrategy.java      # Standard distance-based pricing
│   │   ├── PeakHourFareStrategy.java     # Surge pricing logic
│   │   ├── RideMatchingStrategy.java     # Interface for driver matching
│   │   └── NearestDriverStrategy.java    # Nearest-driver allocation algorithm
│   │
│   └── Main.java                 # Pure Java CLI Console Menu runner
```
---
🗃️ Domain Entities
Entity	Fields	Description
`Rider`	`id`, `name`, `location`	Represents a passenger requesting a ride
`Driver`	`id`, `name`, `currentLocation`, `available`, `vehicleType`	Represents a driver fulfilling rides
`Ride`	`id`, `rider`, `driver`, `distance`, `status`	Tracks the state and details of an active or past trip
`FareReceipt`	`rideId`, `amount`, `generatedAt`	Generated financial summary upon ride completion
---
🚀 Key Features
User & Driver Management: Register riders and drivers with specific vehicle types (`BIKE`, `AUTO`, `CAR`).
Real-time Availability Tracking: Filter and view active drivers ready for dispatch.
Flexible Ride Matching: Plug-and-play strategies to connect riders with available drivers.
Dynamic Fare Calculation: Transparent computation of trip costs based on distance and pricing policies.
Full Lifecycle Tracking: Explicit state machine transitions: `REQUESTED` ➔ `ASSIGNED` ➔ `COMPLETED` / `CANCELLED`.
---
⚙️ Prerequisites
Java JDK: 21
Build Tool: Gradle (Gradle wrapper is included in the project)
---
💻 Getting Started
1. Clone the Repository
```bash
git clone https://github.com/suk2008/RideSharingSystem.git
cd RideSharingSystem
```
2. Run the Application
   You can run the application directly from your terminal using the Gradle wrapper:
   Windows (PowerShell):
```powershell
  .\gradlew run
  ```
macOS / Linux:
```bash
  ./gradlew run
  ```
(Alternatively, open the project in IntelliJ IDEA, navigate to `Main.java`, and click the green Run button next to the `main` method).
---
🖥️ Usage Guide (Console Menu)
When you launch the application, you'll be greeted with an interactive command-line interface:
```text
====================================
           RIDEWISE MENU            
====================================
1. Add Rider
2. Add Driver
3. View Available Drivers
4. Request Ride
5. Complete Ride
6. Cancel Ride
7. View Rides
8. Exit
====================================
Enter your choice: 
```
The menu features built-in input validation and robust exception handling to ensure invalid inputs or edge cases (such as missing IDs or unassigned drivers) won't crash the console session.
---
🔮 Future Extensibility
Because the system relies heavily on interfaces and composition, extending it is straightforward:
New Pricing Models: Implement the `FareStrategy` interface (e.g., `WeekendSurgeStrategy`) and plug it into `RideService`.
Advanced Matching Algorithms: Implement `RideMatchingStrategy` (e.g., `RatingBasedMatchingStrategy`) without changing any core business logic.
Persistence: Easily swap out in-memory collections for a database layer (like JDBC or Hibernate/JPA) thanks to the clean service-repository boundary.