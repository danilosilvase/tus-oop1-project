package com.rentbuy;

import com.rentbuy.property.*;
import com.rentbuy.customer.Customer;
import com.rentbuy.transaction.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Property> properties = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Welcome to RentBuy!");
            boolean running = true;

            while (running) {
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
                        System.out.println("Enter property type (House/Apartment):");
                        String type = scanner.nextLine().toLowerCase();

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

                        if (type.equals("house")) {
                            System.out.println("Does it have a garden? (true/false):");
                            boolean hasGarden = scanner.nextBoolean();
                            scanner.nextLine(); // Consume the newline character
                            House house = new House(address, price, hasGarden);
                            properties.add(house);
                            System.out.println("House added: " + house.getDetails());
                        } else if (type.equals("apartment")) {
                            System.out.println("Enter floor level:");
                            int floorLevel = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character
                            Apartment apartment = new Apartment(address, price, floorLevel);
                            properties.add(apartment);
                            System.out.println("Apartment added: " + apartment.getDetails());
                        } else {
                            System.out.println("Invalid property type.");
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
                        System.out.println("Enter the address of the property to rent:");
                        String rentAddress = scanner.nextLine();
                        Property propertyToRent = findPropertyByAddress(properties, rentAddress);
                        if (propertyToRent != null && propertyToRent.getStatus() == PropertyStatus.AVAILABLE) {
                            propertyToRent.rent();
                            System.out.println("Property rented: " + propertyToRent.getDetails());
                        } else {
                            System.out.println("Property not found or already taken.");
                        }
                    }
                    case 6 -> {
                        System.out.println("Enter the address of the property to purchase:");
                        String purchaseAddress = scanner.nextLine();
                        Property propertyToPurchase = findPropertyByAddress(properties, purchaseAddress);
                        if (propertyToPurchase != null && propertyToPurchase.getStatus() == PropertyStatus.AVAILABLE) {
                            propertyToPurchase.setStatus(PropertyStatus.SOLD);
                            System.out.println("Property purchased: " + propertyToPurchase.getDetails());
                        } else {
                            System.out.println("Property not found or already taken.");
                        }
                    }
                    case 7 -> {
                        System.out.println("Available Properties:");
                        List<Property> availableProperties = properties.stream()
                                .filter(property -> property.getStatus() == PropertyStatus.AVAILABLE)
                                .collect(Collectors.toList());
                        for (Property property : availableProperties) {
                            System.out.println(property.getDetails());
                        }
                    }
                    case 0 -> {
                        running = false;
                        System.out.println("Exiting RentBuy. Goodbye!");
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (PropertyAlreadyTakenException e) {
            System.out.println("Transaction failed: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static Property findPropertyByAddress(List<Property> properties, String address) {
        return properties.stream()
                .filter(property -> property.getAddress().toString().equalsIgnoreCase(address))
                .findFirst()
                .orElse(null);
    }
}
