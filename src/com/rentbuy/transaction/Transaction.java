package com.rentbuy.transaction;

import com.rentbuy.customer.Customer;
import com.rentbuy.property.Property;

import java.time.LocalDate;

public class Transaction {
    private Customer customer;
    private Property property;
    private LocalDate transactionDate;
    private double transactionAmount;

    public Transaction(Customer customer, Property property, LocalDate transactionDate, double transactionAmount) {
        this.customer = customer;
        this.property = property;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
    }

    // Getters and Setters
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

    @Override
    public String toString() {
        return "Transaction: " + property.getDetails() + " sold/rented to " + customer.getName() + " on " + transactionDate + " for " + transactionAmount;
    }
}