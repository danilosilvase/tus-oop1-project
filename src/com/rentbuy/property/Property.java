package com.rentbuy.property;

public abstract class Property {
    private String address;
    private double price;
    private String type;

    public Property(String address, double price, String type) {
        this.address = address;
        this.price = price;
        this.type = type;
    }

    // Getters and Setters
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
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

    // Abstract method for specific details of the property
    public abstract String getDetails();

    @Override
    public String toString() {
        return "Address: " + address + ", Price: " + price + ", Type: " + type;
    }
}
