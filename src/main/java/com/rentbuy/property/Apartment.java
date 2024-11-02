package com.rentbuy.property;

import com.rentbuy.transaction.PropertyAlreadyTakenException;

public final class Apartment extends Property implements Rentable {
    private final int floorLevel;

    // adding default constructor for Jackson
    public Apartment() {
        super(); // Calls the no-argument constructor of Property
        this.floorLevel = 0; // Assigning a default value to floorLevel
    }

    public Apartment(Address address, double price, int floorLevel) {
        super(address, price);
        this.floorLevel = floorLevel;
    }

    public int getFloorLevel() {
        return floorLevel;
    }

    @Override
    public void rent() throws PropertyAlreadyTakenException {
        if (getStatus() != PropertyStatus.AVAILABLE) {
            throw new PropertyAlreadyTakenException("The apartment is already taken.");
        }
        setStatus(PropertyStatus.RENTED);
    }

    @Override
    public String getDetails() {
        return "Apartment: " + getAddress().toString() + ", Price: " + getPrice() + ", Status: " + getStatus() + ", Floor Level: " + getFloorLevel();
    }
}
