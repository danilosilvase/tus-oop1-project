package com.rentbuy.transaction;

// Custom exception to indicate that a property is already rented or sold.
public class PropertyAlreadyTakenException extends Exception {
    public PropertyAlreadyTakenException(String message) {
        super(message);
    }
}
