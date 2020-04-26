package com.gupao.vip.drools.edu.entity;

public class Address {

    private String postcode;
    private String street;
    private String state;

    public Address() {
    }

    public Address(String postcode, String street, String state) {
        this.postcode = postcode;
        this.street = street;
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
