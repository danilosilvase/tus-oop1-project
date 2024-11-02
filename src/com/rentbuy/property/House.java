package com.rentbuy.property;

// Final class House extends sealed Property class
public final class House extends Property implements Rentable {
    private boolean hasGarden;

    // Constructor for House class
    public House(String address, double price, boolean hasGarden) {
        super(address, price, "House");
        this.hasGarden = hasGarden;
    }

    // Getter for hasGarden
    public boolean hasGarden() {
        return hasGarden;
    }

    // Setter for hasGarden
    public void setHasGarden(boolean hasGarden) {
        this.hasGarden = hasGarden;
    }

    // Implementation of abstract method from Property class
    @Override
    public String getDetails() {
        return super.toString() + ", Has Garden: " + hasGarden;
    }

    // Implementation of rent method from Rentable interface
    @Override
    public void rent() {
        System.out.println("House at " + getAddress() + " has been rented.");
    }
}
