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

## Next Steps

- **More Use Cases**: Expand the functionality to include additional use cases such as removing properties, updating customer information, and advanced search capabilities.
- **Advanced Java Features**: Implement more features from Java 22/23 to gain extra marks, such as exploring new language syntax or APIs.
- **Improved Documentation**: Add more detailed comments to the code, create a comprehensive user manual, and provide additional UML diagrams for better architectural understanding.

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
