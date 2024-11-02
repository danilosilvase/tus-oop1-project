package com.rentbuy.property;

// Final class Apartment extends sealed Property class
public final class Apartment extends Property implements Rentable {
    private int floorLevel;

    // Constructor for Apartment class
    public Apartment(String address, double price, int floorLevel) {
        super(address, price, "Apartment");
        this.floorLevel = floorLevel;
    }

    // Getter for floorLevel
    public int getFloorLevel() {
        return floorLevel;
    }

    // Setter for floorLevel
    public void setFloorLevel(int floorLevel) {
        this.floorLevel = floorLevel;
    }

    // Implementation of abstract method from Property class
    @Override
    public String getDetails() {
        return super.toString() + ", Floor Level: " + floorLevel;
    }

    // Implementation of rent method from Rentable interface
    @Override
    public void rent() {
        System.out.println("Apartment at " + getAddress() + " on floor " + floorLevel + " has been rented.");
    }
}
