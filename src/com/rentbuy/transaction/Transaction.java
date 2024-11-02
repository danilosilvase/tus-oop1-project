package com.rentbuy.transaction;

import com.rentbuy.customer.Customer;
import com.rentbuy.property.Property;
import com.rentbuy.property.PropertyStatus;

import java.time.LocalDate;

public class Transaction {
    private Customer customer;
    private Property property;
    private LocalDate transactionDate;
    private double transactionAmount;
    private boolean isCompleted;

    // Constructor for Transaction class
    public Transaction(Customer customer, Property property, LocalDate transactionDate, double transactionAmount) throws PropertyAlreadyTakenException {
        // Check if the property is already sold/rented.
        if (property.getStatus() != PropertyStatus.AVAILABLE) {
            throw new PropertyAlreadyTakenException("The property is already sold or rented.");
        }

        this.customer = customer;
        this.property = property;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
        this.isCompleted = true; // Mark transaction as completed
        property.setStatus(PropertyStatus.SOLD); // Update property status to SOLD
    }

    // Getters and setters
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    // Overriding toString to provide transaction details
    @Override
    public String toString() {
        return "Transaction: " + property.getDetails() + " sold/rented to " + customer.getName() + " on " + transactionDate + " for " + transactionAmount;
    }
}
