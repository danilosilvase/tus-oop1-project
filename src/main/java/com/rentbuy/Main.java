package com.rentbuy;

import com.rentbuy.property.*;
import com.rentbuy.customer.Customer;
import com.rentbuy.transaction.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// adding import to excercise features from javam 22/23
import java.util.concurrent.StructuredTaskScope;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.jsontype.NamedType;

public class Main {
    private static final String DATA_FOLDER = "data";
    private static final List<Property> properties = new ArrayList<>();
    private static final List<Customer> customers = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to RentBuy!");
        ensureDataFolderExists();
        loadData(); // load data at the start
        boolean running = true;

        while (running) {
            printMenu();
            int choice = getUserInputInt("Select an option:");
            switch (choice) {
                case 1 -> createProperty();
                case 2 -> createCustomer();
                case 3 -> viewAllProperties();
                case 4 -> viewAllCustomers();
                case 5 -> rentProperty();
                case 6 -> purchaseProperty();
                case 7 -> viewAvailableProperties();
                case 8 -> removeCustomer();
                case 9 -> removeProperty();
                case 0 -> {
                    saveData(); // save persistent data before exiting
                    running = false;
                    System.out.println("Exiting RentBuy. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\nSelect an option:");
        System.out.println("1. Create a new property");
        System.out.println("2. Create a new customer");
        System.out.println("3. View all properties");
        System.out.println("4. View all customers");
        System.out.println("5. Rent a property");
        System.out.println("6. Purchase a property");
        System.out.println("7. View available properties");
        System.out.println("8. Remove a customer");
        System.out.println("9. Remove a property");
        System.out.println("0. Exit");
    }

    private static void createProperty() {
        System.out.println("Select property type:");
        System.out.println("1. House");
        System.out.println("2. Apartment");
        int propertyTypeChoice = getUserInputInt("Enter choice:");

        if (propertyTypeChoice != 1 && propertyTypeChoice != 2) {
            System.out.println("Invalid property type selection.");
            return;
        }

        Address address = getAddressInput();
        double price = getUserInputDouble("Enter price:");

        if (propertyTypeChoice == 1) {
            boolean hasGarden = getUserInputBoolean("Does it have a garden? (true/false):");
            House house = new House(address, price, hasGarden);
            properties.add(house);
            System.out.println("House added: " + house.getDetails());
        } else {
            int floorLevel = getUserInputInt("Enter floor level:");
            Apartment apartment = new Apartment(address, price, floorLevel);
            properties.add(apartment);
            System.out.println("Apartment added: " + apartment.getDetails());
        }
    }

    private static void createCustomer() {
        System.out.println("Enter customer name:");
        String name = scanner.nextLine();
        System.out.println("Enter contact information:");
        String contactInfo = scanner.nextLine();
        boolean interestedInRenting = getUserInputBoolean("Is the customer interested in renting? (true/false):");

        Customer customer = new Customer(name, contactInfo, interestedInRenting);
        customers.add(customer);
        System.out.println("Customer added: " + customer);
    }

    private static void viewAllProperties() {
        if (properties.isEmpty()) {
            System.out.println("No properties available.");
        } else {
            System.out.println("All Properties:");
            properties.forEach(property -> System.out.println(property.getDetails()));
        }
    }

    private static void viewAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
        } else {
            System.out.println("All Customers:");
            customers.forEach(System.out::println);
        }
    }

    private static void rentProperty() {
        Customer customer = getCustomerByName();
        if (customer == null)
            return;

        List<Property> availableProperties = properties.stream()
                .filter(property -> property.getStatus() == PropertyStatus.AVAILABLE)
                .collect(Collectors.toList());

        if (availableProperties.isEmpty()) {
            System.out.println("No available properties for rent.");
            return;
        }

        Property propertyToRent = selectPropertyFromList(availableProperties, "Available Properties:");
        if (propertyToRent != null) {
            try {
                propertyToRent.rent();
                System.out.println("Property rented successfully: " + propertyToRent.getDetails());
            } catch (PropertyAlreadyTakenException e) {
                System.out.println("Transaction failed: " + e.getMessage());
            }
        }
    }

    private static void purchaseProperty() {
        Customer customer = getCustomerByName();
        if (customer == null)
            return;

        List<Property> availableProperties = properties.stream()
                .filter(property -> property.getStatus() == PropertyStatus.AVAILABLE)
                .collect(Collectors.toList());

        if (availableProperties.isEmpty()) {
            System.out.println("No available properties for purchase.");
            return;
        }

        Property propertyToPurchase = selectPropertyFromList(availableProperties, "Available Properties for Purchase:");
        if (propertyToPurchase != null) {
            propertyToPurchase.setStatus(PropertyStatus.SOLD);
            System.out.println("Property purchased successfully by " + customer.getName() + ": "
                    + propertyToPurchase.getDetails());
        }
    }

    private static void viewAvailableProperties() {
        List<Property> availableProperties = properties.stream()
                .filter(property -> property.getStatus() == PropertyStatus.AVAILABLE)
                .collect(Collectors.toList());
        if (availableProperties.isEmpty()) {
            System.out.println("No available properties at the moment.");
        } else {
            System.out.println("Available Properties:");
            availableProperties.forEach(property -> System.out.println(property.getDetails()));
        }
    }

    private static void removeCustomer() {
        Customer customerToRemove = getCustomerByName();
        if (customerToRemove != null) {
            customers.remove(customerToRemove);
            System.out.println("Customer removed successfully: " + customerToRemove.getName());
        }
    }

    private static void removeProperty() {
        if (properties.isEmpty()) {
            System.out.println("No properties available to remove.");
            return;
        }

        Property propertyToRemove = selectPropertyFromList(properties, "Available Properties for Removal:");
        if (propertyToRemove != null) {
            properties.remove(propertyToRemove);
            System.out.println("Property removed successfully: " + propertyToRemove.getDetails());
        }
    }

    // methods for user input
    private static int getUserInputInt(String prompt) {
        System.out.println(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    private static double getUserInputDouble(String prompt) {
        System.out.println(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next();
        }
        double input = scanner.nextDouble();
        scanner.nextLine();
        return input;
    }

    private static boolean getUserInputBoolean(String prompt) {
        System.out.println(prompt);
        while (!scanner.hasNextBoolean()) {
            System.out.println("Invalid input. Please enter true or false.");
            scanner.next();
        }
        boolean input = scanner.nextBoolean();
        scanner.nextLine();
        return input;
    }

    private static Address getAddressInput() {
        System.out.println("Enter street address:");
        String street = scanner.nextLine();
        System.out.println("Enter city:");
        String city = scanner.nextLine();
        System.out.println("Enter country:");
        String country = scanner.nextLine();
        return new Address(street, city, country);
    }

    private static Customer getCustomerByName() {
        System.out.println("Enter customer name:");
        String customerName = scanner.nextLine();
        Customer customer = findCustomerByName(customers, customerName);
        if (customer == null) {
            System.out.println("Customer not found. Please create the customer first.");
        }
        return customer;
    }

    private static Property selectPropertyFromList(List<Property> propertyList, String message) {
        System.out.println(message);
        for (int i = 0; i < propertyList.size(); i++) {
            System.out.println((i + 1) + ". " + propertyList.get(i).getDetails());
        }

        int propertyIndex = getUserInputInt("Enter the number of the property:") - 1;
        if (propertyIndex < 0 || propertyIndex >= propertyList.size()) {
            System.out.println("Invalid property selection.");
            return null;
        }
        return propertyList.get(propertyIndex);
    }

    // method for finding cudstomer by name
    private static Customer findCustomerByName(List<Customer> customers, String name) {
        return customers.stream()
                .filter(customer -> customer.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    // add load data function toexercise featurea from java 22/23

    // private static ObjectMapper getConfiguredObjectMapper() {
    //     ObjectMapper mapper = new ObjectMapper();
    //     // This configures Jackson to use default typing, which ensures type information
    //     // is included in serialization.
    //     mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.EVERYTHING);
    //     return mapper;
    // }

    private static void ensureDataFolderExists() {
        try {
            Files.createDirectories(Paths.get(DATA_FOLDER));
        } catch (IOException e) {
            System.out.println("Error creating data folder: " + e.getMessage());
        }
    }

    private static void loadData() {
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<Void>()) {
            scope.fork(() -> {
                loadPropertiesData();
                return null;
            });
            scope.fork(() -> {
                loadCustomersData();
                return null;
            });

            scope.join();
        } catch (Exception e) {
            System.out.println("Error during loading data: " + e.getMessage());
        }
    }

    private static void loadPropertiesData() {
        ObjectMapper mapper = getConfiguredObjectMapper();
        try {
            properties.addAll(
                    mapper.readValue(new File(DATA_FOLDER + "/properties.json"), new TypeReference<List<Property>>() {
                    }));
            System.out.println("Properties loaded successfully.");
        } catch (IOException e) {
            System.out.println("Failed to load properties: " + e.getMessage());
        }
    }

    private static void loadCustomersData() {
        ObjectMapper mapper = getConfiguredObjectMapper();
        try {
            customers.addAll(
                    mapper.readValue(new File(DATA_FOLDER + "/customers.json"), new TypeReference<List<Customer>>() {
                    }));
            System.out.println("Customers loaded successfully.");
        } catch (IOException e) {
            System.out.println("Failed to load customers: " + e.getMessage());
        }
    }

    private static void saveData() {
        ObjectMapper mapper = getConfiguredObjectMapper();
        try {
            mapper.writeValue(new File(DATA_FOLDER + "/properties.json"), properties);
            System.out.println("Properties saved successfully.");
        } catch (IOException e) {
            System.out.println("Failed to save properties: " + e.getMessage());
        }

        try {
            mapper.writeValue(new File(DATA_FOLDER + "/customers.json"), customers);
            System.out.println("Customers saved successfully.");
        } catch (IOException e) {
            System.out.println("Failed to save customers: " + e.getMessage());
        }
    }

    private static ObjectMapper getConfiguredObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.EVERYTHING);
        mapper.registerSubtypes(
                new NamedType(House.class, "house"),
                new NamedType(Apartment.class, "apartment"));
        return mapper;
    }

}