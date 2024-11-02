package com.rentbuy.property;

public class House extends Property {
    private boolean hasGarden;

    public House(String address, double price, boolean hasGarden) {
        super(address, price, "House");
        this.hasGarden = hasGarden;
    }

    public boolean hasGarden() {
        return hasGarden;
    }

    public void setHasGarden(boolean hasGarden) {
        this.hasGarden = hasGarden;
    }

    @Override
    public String getDetails() {
        return super.toString() + ", Has Garden: " + hasGarden;
    }
}