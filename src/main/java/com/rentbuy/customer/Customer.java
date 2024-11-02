package com.rentbuy.customer;

public class Customer {
    private String name;
    private String contactInfo;
    private boolean isInterestedInRenting;

    public Customer(String name, String contactInfo, boolean isInterestedInRenting) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.isInterestedInRenting = isInterestedInRenting;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public boolean isInterestedInRenting() {
        return isInterestedInRenting;
    }

    public void setInterestedInRenting(boolean interestedInRenting) {
        isInterestedInRenting = interestedInRenting;
    }

    @Override
    public String toString() {
        return "Customer: " + name + ", Contact Info: " + contactInfo + ", Interested in Renting: " + isInterestedInRenting;
    }
}
