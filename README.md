# RentBuy Project Documentation

## Introduction

The **RentBuy** application is designed to demonstrate object-oriented programming principles in Java while covering both fundamental and advanced language features introduced in the latest LTS (Java 21). The application simulates a real estate company that handles the selling and renting of properties, specifically houses and apartments. The application is developed to showcase a range of Java capabilities such as encapsulation, inheritance, interfaces, lambda expressions, and more.

The core functionality revolves around managing properties, customers, and transactions. Users can view details about houses and apartments, and initiate transactions such as renting or purchasing properties. The project emphasizes clean code, use of Java's newest features, and follows best practices for object-oriented design.

## Features

### Property Creation and Management
- Create new properties (houses and apartments) with details such as address, price, and additional attributes (e.g., garden for houses, floor level for apartments).
- View details of available properties.

### Customer Management
- Create customers with basic information such as name and contact details.

### Transaction Handling
- Initiate a transaction to rent or purchase a property.
- Get notified if a property is already sold or rented.

### Property Status Updates
- See the status of properties (AVAILABLE, RENTED, SOLD) and how it changes after a transaction.

### List Available Properties
- Filter and view only the available properties for rent or purchase.

## Evaluation

### Fundamentals
- **Classes and Encapsulation**: The project makes use of multiple classes to represent entities like `House`, `Apartment`, `Customer`, and `Transaction`. Encapsulation is achieved through private fields and public getter/setter methods.
- **Inheritance and Polymorphism**: `House` and `Apartment` inherit from an abstract class `Property`. Polymorphism is utilized in the way transactions handle different property types.
- **Interfaces**: The `Rentable` interface is implemented to define rental behavior for properties.
- **Exceptions**: Custom exceptions like `PropertyAlreadyTakenException` are used to handle business logic errors such as attempting to rent or sell an already occupied property.
- **Enums**: The `PropertyStatus` enum is used to represent the status of properties (AVAILABLE, RENTED, SOLD).
- **Java Core API**: The project uses classes from the Java Core API, such as `StringBuilder`, `List`, and `LocalDate` for string manipulation, collections, and date handling.

### Advanced Features
- **Records**: The `Address` record is used to represent immutable address data, simplifying the code and ensuring immutability.
- **Lambda Expressions and Method References**: Lambda expressions are used for filtering available properties. Method references could be added to further simplify code where applicable.
- **Sealed Classes**: The `Property` class is a sealed class, allowing only `House` and `Apartment` to extend it, ensuring greater control over inheritance.
- **Defensive Copying**: Defensive copying is implemented in scenarios where mutable objects are returned to ensure data integrity.
- **Discussion of `final` and Effective Final**: The use of `final` variables is demonstrated to indicate immutability where necessary, ensuring values do not change after assignment.

### Problems Encountered
- **JUnit Integration**: Initially, integrating JUnit 5 for testing was challenging due to version compatibility issues. This was resolved by ensuring consistent versions of `junit-jupiter-api` and `junit-jupiter-engine`.
- **Directory Structure**: Adjusting the project to follow the standard Maven directory layout required restructuring the codebase. This change improved the organization but required some path adjustments during compilation and testing.

## Use Cases

### Use Case 1: Create a New Property
- **Actor**: Admin/User
- **Description**: The user creates a new property by providing details such as address, price, type (House or Apartment), and additional attributes like garden or floor level.
- **Preconditions**: The user must be logged in as an admin.
- **Postconditions**: A new property is added to the system and marked as AVAILABLE.
- **Main Flow**:
  1. User selects the option to create a new property.
  2. User provides property details.
  3. System validates the input and saves the property.
  4. Confirmation message is displayed.

### Use Case 2: Create a Customer
- **Actor**: Admin/User
- **Description**: The user creates a new customer with basic information such as name, contact details, and renting preferences.
- **Preconditions**: The user must have the necessary permissions.
- **Postconditions**: The customer is added to the system.
- **Main Flow**:
  1. User selects the option to add a new customer.
  2. User provides customer details (name, contact information, renting preference).
  3. System validates and saves the customer.
  4. Confirmation message is displayed.

### Use Case 3: Rent a Property
- **Actor**: Customer
- **Description**: A customer rents a property that is marked as AVAILABLE.
- **Preconditions**: The property must be available, and the customer must be registered.
- **Postconditions**: The property status is updated to RENTED.
- **Main Flow**:
  1. Customer selects an available property to rent.
  2. System checks the availability of the property.
  3. System updates the property status to RENTED.
  4. Confirmation message is displayed.

### Use Case 4: Purchase a Property
- **Actor**: Customer
- **Description**: A customer purchases a property that is marked as AVAILABLE.
- **Preconditions**: The property must be available, and the customer must be registered.
- **Postconditions**: The property status is updated to SOLD.
- **Main Flow**:
  1. Customer selects an available property to purchase.
  2. System checks the availability of the property.
  3. System updates the property status to SOLD.
  4. Confirmation message is displayed.

### Use Case 5: View Available Properties
- **Actor**: Customer/User
- **Description**: The user views a list of properties that are available for rent or purchase.
- **Preconditions**: None.
- **Postconditions**: A list of available properties is displayed.
- **Main Flow**:
  1. User selects the option to view available properties.
  2. System retrieves properties marked as AVAILABLE.
  3. The list of available properties is displayed to the user.

### Use Case 6: Handle Property Already Taken Exception
- **Actor**: Customer/User
- **Description**: The system handles an exception when a customer tries to rent or purchase a property that is already taken.
- **Preconditions**: The property must be marked as RENTED or SOLD.
- **Postconditions**: The customer is notified that the property is not available.
- **Main Flow**:
  1. Customer attempts to rent or purchase a property.
  2. System checks the property status.
  3. If the property is already taken, the system throws `PropertyAlreadyTakenException`.
  4. An error message is displayed to the customer.

## How to Run the Project

1. **Prerequisites**: Ensure you have Java 21 installed and set up correctly.
2. **Clone the Repository**: Clone the project from the repository.
3. **Compile the Code**:
   - Navigate to the root directory and run `javac -cp .:lib/* -d out/main src/main/java/com/rentbuy/**/*.java` to compile the main code.
   - Run `javac -cp .:lib/*:out/main -d out/test src/test/java/com/rentbuy/**/*.java` to compile the tests.
4. **Run the Application**:
   - Execute the main class using `java -cp .:out/main com.rentbuy.Main`.
5. **Run Tests**:
   - Use `java -cp .:lib/*:out/test:out/main org.junit.platform.console.ConsoleLauncher --scan-class-path` to run the JUnit tests.

## Contact
For any questions or clarifications, feel free to reach out.