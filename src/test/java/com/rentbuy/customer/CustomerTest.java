package com.rentbuy.customer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer("Alice Doe", "alice.doe@example.com", true);

        assertEquals("Alice Doe", customer.getName());
        assertEquals("alice.doe@example.com", customer.getContactInfo());
        assertTrue(customer.isInterestedInRenting());
    }
}
