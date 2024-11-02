package com.rentbuy;

import com.rentbuy.property.*;
import com.rentbuy.customer.Customer;
import com.rentbuy.transaction.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Property> properties = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to RentBuy!");
        boolean running = true;

        while (running) {
            try {
                System.out.println("\nSelect an option:");
                System.out.println("1. Create a new property");
                System.out.println("2. Create a new customer");
                System.out.println("3. View all properties");
                System.out.println("4. View all customers");
                System.out.println("5. Rent a property");
                System.out.println("6. Purchase a property");
                System.out.println("7. View available properties");
                System.out.println("0. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1 -> {
                        System.out.println("Select property type:");
                        System.out.println("1. House");
                        System.out.println("2. Apartment");
                        int propertyTypeChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character

                        if (propertyTypeChoice != 1 && propertyTypeChoice != 2) {
                            System.out.println("Invalid property type selection. Please choose either 1 (House) or 2 (Apartment).");
                            break;
                        }

                        System.out.println("Enter street address:");
                        String street = scanner.nextLine();
                        System.out.println("Enter city:");
                        String city = scanner.nextLine();
                        System.out.println("Enter country:");
                        String country = scanner.nextLine();
                        Address address = new Address(street, city, country);

                        System.out.println("Enter price:");
                        double price = scanner.nextDouble();
                        scanner.nextLine(); // Consume the newline character

                        if (propertyTypeChoice == 1) {
                            System.out.println("Does it have a garden? (true/false):");
                            boolean hasGarden = scanner.nextBoolean();
                            scanner.nextLine(); // Consume the newline character
                            House house = new House(address, price, hasGarden);
                            properties.add(house);
                            System.out.println("House added: " + house.getDetails());
                        } else if (propertyTypeChoice == 2) {
                            System.out.println("Enter floor level:");
                            int floorLevel = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character
                            Apartment apartment = new Apartment(address, price, floorLevel);
                            properties.add(apartment);
                            System.out.println("Apartment added: " + apartment.getDetails());
                        }
                    }

                    case 2 -> {
                        System.out.println("Enter customer name:");
                        String name = scanner.nextLine();

                        System.out.println("Enter contact information:");
                        String contactInfo = scanner.nextLine();

                        System.out.println("Is the customer interested in renting? (true/false):");
                        boolean interestedInRenting = scanner.nextBoolean();
                        scanner.nextLine(); // Consume the newline character

                        Customer customer = new Customer(name, contactInfo, interestedInRenting);
                        customers.add(customer);
                        System.out.println("Customer added: " + customer);
                    }
                    case 3 -> {
                        System.out.println("All Properties:");
                        for (Property property : properties) {
                            System.out.println(property.getDetails());
                        }
                    }
                    case 4 -> {
                        System.out.println("All Customers:");
                        for (Customer customer : customers) {
                            System.out.println(customer);
                        }
                    }
                    case 5 -> {
                        System.out.println("Enter customer name:");
                        String customerName = scanner.nextLine();
                        Customer customer = findCustomerByName(customers, customerName);

                        if (customer == null) {
                            System.out.println("Customer not found. Please create the customer first.");
                            break;
                        }

                        List<Property> availableProperties = properties.stream()
                                .filter(property -> property.getStatus() == PropertyStatus.AVAILABLE)
                                .collect(Collectors.toList());

                        if (availableProperties.isEmpty()) {
                            System.out.println("No available properties for rent.");
                            break;
                        }

                        System.out.println("Available Properties:");
                        for (int i = 0; i < availableProperties.size(); i++) {
                            System.out.println((i + 1) + ". " + availableProperties.get(i).getDetails());
                        }

                        System.out.println("Enter the number of the property to rent:");
                        int propertyIndex = scanner.nextInt() - 1;
                        scanner.nextLine(); // Consume the newline character

                        if (propertyIndex < 0 || propertyIndex >= availableProperties.size()) {
                            System.out.println("Invalid property selection.");
                        } else {
                            Property propertyToRent = availableProperties.get(propertyIndex);
                            try {
                                propertyToRent.rent();
                                System.out.println("Property rented successfully: " + propertyToRent.getDetails());
                            } catch (PropertyAlreadyTakenException e) {
                                System.out.println("Transaction failed: " + e.getMessage());
                            }
                        }
                    }
                    case 6 -> {
                        System.out.println("Enter customer name:");
                        String customerName = scanner.nextLine();
                        Customer customer = findCustomerByName(customers, customerName);

                        if (customer == null) {
                            System.out.println("Customer not found. Please create the customer first.");
                            break;
                        }

                        List<Property> availablePropertiesForPurchase = properties.stream()
                                .filter(property -> property.getStatus() == PropertyStatus.AVAILABLE)
                                .collect(Collectors.toList());

                        if (availablePropertiesForPurchase.isEmpty()) {
                            System.out.println("No available properties for purchase.");
                            break;
                        }

                        System.out.println("Available Properties for Purchase:");
                        for (int i = 0; i < availablePropertiesForPurchase.size(); i++) {
                            System.out.println((i + 1) + ". " + availablePropertiesForPurchase.get(i).getDetails());
                        }

                        System.out.println("Enter the number of the property to purchase:");
                        int propertyIndex = scanner.nextInt() - 1;
                        scanner.nextLine();

                        if (propertyIndex < 0 || propertyIndex >= availablePropertiesForPurchase.size()) {
                            System.out.println("Invalid property selection. Please try again.");
                        } else {
                            Property propertyToPurchase = availablePropertiesForPurchase.get(propertyIndex);
                            propertyToPurchase.setStatus(PropertyStatus.SOLD);
                            System.out.println("Property purchased successfully by " + customer.getName() + ": " + propertyToPurchase.getDetails());
                        }
                    }

                    case 7 -> {
                        System.out.println("Available Properties:");
                        List<Property> availableProperties = properties.stream()
                                .filter(property -> property.getStatus() == PropertyStatus.AVAILABLE)
                                .collect(Collectors.toList());
                        if (availableProperties.isEmpty()) {
                            System.out.println("No available properties at the moment.");
                        } else {
                            for (Property property : availableProperties) {
                                System.out.println(property.getDetails());
                            }
                        }
                    }

                    case 8 -> {
                        System.out.println("Enter the customer name to remove:");
                        String customerNameToRemove = scanner.nextLine();
                        Customer customerToRemove = findCustomerByName(customers, customerNameToRemove);

                        if (customerToRemove == null) {
                            System.out.println("Customer not found.");
                        } else {
                            customers.remove(customerToRemove);
                            System.out.println("Customer removed successfully: " + customerToRemove.getName());
                        }
                    }

                    case 9 -> {
                        System.out.println("Enter the address of the property to remove:");
                        String addressToRemove = scanner.nextLine();
                        Property propertyToRemove = findPropertyByAddress(properties, addressToRemove);

                        if (propertyToRemove == null) {
                            System.out.println("Property not found.");
                        } else {
                            properties.remove(propertyToRemove);
                            System.out.println("Property removed successfully: " + propertyToRemove.getDetails());
                        }
                    }

                    case 0 -> {
                        running = false;
                        System.out.println("Exiting RentBuy. Goodbye!");
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number corresponding to the menu options.");
                scanner.nextLine(); 
            }
        }

        scanner.close();
    }

    private static Property findPropertyByAddress(List<Property> properties, String address) {
        return properties.stream()
                .filter(property -> property.getAddress().toString().equalsIgnoreCase(address))
                .findFirst()
                .orElse(null);
    }

    private static Customer findCustomerByName(List<Customer> customers, String name) {
        return customers.stream()
                .filter(customer -> customer.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
