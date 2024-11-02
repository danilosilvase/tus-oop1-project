package com.rentbuy;

import com.rentbuy.property.House;
import com.rentbuy.property.Apartment;
import com.rentbuy.customer.Customer;
import com.rentbuy.transaction.Transaction;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        House house = new House("123 Green St, Dublin", 350000, true);
        Apartment apartment = new Apartment("456 Blue Ave, Dublin", 250000, 5);

        System.out.println("House Details: " + house.getDetails());
        System.out.println("Apartment Details: " + apartment.getDetails());

        // Create a customer
        Customer customer = new Customer("John Doe", "john.doe@example.com", true);
        System.out.println(customer);

        // Create a transaction for the house
        Transaction transaction = new Transaction(customer, house, LocalDate.now(), 350000);
        System.out.println(transaction);
    }
}
