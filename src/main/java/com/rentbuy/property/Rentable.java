package com.rentbuy.property;

import com.rentbuy.transaction.PropertyAlreadyTakenException;

public interface Rentable {
    void rent() throws PropertyAlreadyTakenException;
}
