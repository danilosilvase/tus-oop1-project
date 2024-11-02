package com.rentbuy.property;

import com.rentbuy.transaction.PropertyAlreadyTakenException;

public final class House extends Property implements Rentable {
    private final boolean hasGarden;

    // adding default constructor for Jackson
    public House() {
        super(); // Calls the no-argument constructor of Property
        this.hasGarden = false; // Assigning a default value to hasGarden
    }

    public House(Address address, double price, boolean hasGarden) {
        super(address, price);
        this.hasGarden = hasGarden;
    }

    public boolean hasGarden() {
        return hasGarden;
    }

    @Override
    public void rent() throws PropertyAlreadyTakenException {
        if (getStatus() != PropertyStatus.AVAILABLE) {
            throw new PropertyAlreadyTakenException("The house is already taken.");
        }
        setStatus(PropertyStatus.RENTED);
    }

    @Override
    public String getDetails() {
        return "House: " + getAddress().toString() + ", Price: " + getPrice() + ", Status: " + getStatus() + ", Has Garden: " + hasGarden();
    }
}
