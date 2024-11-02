package com.rentbuy;

import com.rentbuy.property.House;
import com.rentbuy.property.Apartment;

public class Main {
    public static void main(String[] args) {
        House house = new House("123 Green St, Dublin", 350000, true);
        Apartment apartment = new Apartment("456 Blue Ave, Dublin", 250000, 5);

        System.out.println("House Details: " + house.getDetails());
        System.out.println("Apartment Details: " + apartment.getDetails());
    }
}