package com.rentbuy.property;

import com.rentbuy.transaction.PropertyAlreadyTakenException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = House.class, name = "com.rentbuy.property.House"),
    @JsonSubTypes.Type(value = Apartment.class, name = "com.rentbuy.property.Apartment")
})
public abstract sealed class Property permits House, Apartment {
    private final Address address;
    private double price;
    private PropertyStatus status;

    protected Property() {
        this.address = null;
        this.price = 0.0;
        this.status = PropertyStatus.AVAILABLE;
    }

    public Property(Address address, double price) {
        this.address = address;
        this.price = price;
        this.status = PropertyStatus.AVAILABLE;
    }

    public Address getAddress() {
        return address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public PropertyStatus getStatus() {
        return status;
    }

    public void setStatus(PropertyStatus status) {
        this.status = status;
    }

    @JsonIgnore // This ensures 'details' won't be serialized
    public String getDetails() {
        return "Address: " + address + ", Price: " + price + ", Status: " + status;
    }

    public abstract void rent() throws PropertyAlreadyTakenException;
}
