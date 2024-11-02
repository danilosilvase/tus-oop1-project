package com.rentbuy.property;

public class Apartment extends Property {
    private int floorLevel;

    public Apartment(String address, double price, int floorLevel) {
        super(address, price, "Apartment");
        this.floorLevel = floorLevel;
    }

    public int getFloorLevel() {
        return floorLevel;
    }

    public void setFloorLevel(int floorLevel) {
        this.floorLevel = floorLevel;
    }

    @Override
    public String getDetails() {
        return super.toString() + ", Floor Level: " + floorLevel;
    }
}