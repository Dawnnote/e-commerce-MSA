package com.example.orderservice;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {
    private String state;
    private String city;
    private String zipcode;
    private String street;

    protected Address() {}

    public Address(String state, String city, String zipcode, String street) {
        this.state = state;
        this.city = city;
        this.zipcode = zipcode;
        this.street = street;
    }
}
