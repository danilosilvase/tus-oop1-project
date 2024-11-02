package com.rentbuy.property;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PropertyTest {

    @Test
    public void testHouseCreation() {
        Address address = new Address("123 Green St", "Dublin", "Ireland");
        House house = new House(address, 350000, true);

        assertEquals("123 Green St", house.getAddress().street());
        assertEquals("Dublin", house.getAddress().city());
        assertEquals("Ireland", house.getAddress().country());
        assertEquals(350000, house.getPrice());
        assertEquals(PropertyStatus.AVAILABLE, house.getStatus());
        assertTrue(house.hasGarden());
    }

    @Test
    public void testApartmentCreation() {
        Address address = new Address("456 Blue Ave", "Dublin", "Ireland");
        Apartment apartment = new Apartment(address, 250000, 5);

        assertEquals("456 Blue Ave", apartment.getAddress().street());
        assertEquals("Dublin", apartment.getAddress().city());
        assertEquals("Ireland", apartment.getAddress().country());
        assertEquals(250000, apartment.getPrice());
        assertEquals(PropertyStatus.AVAILABLE, apartment.getStatus());
        assertEquals(5, apartment.getFloorLevel());
    }
}
