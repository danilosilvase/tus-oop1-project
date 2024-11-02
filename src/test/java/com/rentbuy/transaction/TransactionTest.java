package com.rentbuy.transaction;

import com.rentbuy.customer.Customer;
import com.rentbuy.property.Address;
import com.rentbuy.property.House;
import com.rentbuy.property.PropertyStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class TransactionTest {

    @Test
    public void testSuccessfulTransaction() {
        Address address = new Address("123 Green St", "Dublin", "Ireland");
        House house = new House(address, 350000, true);
        Customer customer = new Customer("Jane Doe", "jane.doe@example.com", true);

        try {
            Transaction transaction = new Transaction(customer, house, LocalDate.now(), 350000);
            assertEquals(PropertyStatus.SOLD, house.getStatus());
            assertEquals(customer, transaction.getCustomer());
        } catch (PropertyAlreadyTakenException e) {
            fail("Transaction failed, but it should have succeeded.");
        }
    }

    @Test
    public void testFailedTransaction() {
        Address address = new Address("123 Green St", "Dublin", "Ireland");
        House house = new House(address, 350000, true);
        Customer customer1 = new Customer("Jane Doe", "jane.doe@example.com", true);
        Customer customer2 = new Customer("John Smith", "john.smith@example.com", true);

        try {
            // First transaction succeeds
            new Transaction(customer1, house, LocalDate.now(), 350000);

            // Second transaction should fail since the house is already sold
            assertThrows(PropertyAlreadyTakenException.class, () -> {
                new Transaction(customer2, house, LocalDate.now(), 350000);
            });
        } catch (PropertyAlreadyTakenException e) {
            fail("Transaction failed unexpectedly.");
        }
    }
}
