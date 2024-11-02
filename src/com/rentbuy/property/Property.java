package com.rentbuy.property;

// Sealed class Property, allowing only House and Apartment to extend it
public sealed abstract class Property permits House, Apartment {
    private Address address;
    private double price;
    private String type;
    private PropertyStatus status;

    // Constructor
    public Property(Address address, double price, String type) {
        this.address = address;
        this.price = price;
        this.type = type;
        this.status = PropertyStatus.AVAILABLE; // Default status is AVAILABLE
    }

    // Getters and Setters
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PropertyStatus getStatus() {
        return status;
    }

    public void setStatus(PropertyStatus status) {
        this.status = status;
    }

    // Abstract method for specific details of the property
    public abstract String getDetails();

    @Override
    public String toString() {
        return "Address: " + address.street() + ", " + address.city() + ", " + address.country() +
                ", Price: " + price + ", Type: " + type + ", Status: " + status;
    }
}
