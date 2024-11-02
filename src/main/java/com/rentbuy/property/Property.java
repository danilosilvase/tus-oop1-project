package com.rentbuy.property;

import com.rentbuy.transaction.PropertyAlreadyTakenException;

public abstract sealed class Property permits House, Apartment {
    private final Address address;
    private double price;
    private PropertyStatus status;

    public Property(Address address, double price) {
        this.address = address;
        this.price = price;
        this.status = PropertyStatus.AVAILABLE;
    }

    public Address getAddress() {
        return address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public PropertyStatus getStatus() {
        return status;
    }

    public void setStatus(PropertyStatus status) {
        this.status = status;
    }

    // Add getDetails method
    public String getDetails() {
        return "Address: " + address + ", Price: " + price + ", Status: " + status;
    }

    public abstract void rent() throws PropertyAlreadyTakenException;
}
