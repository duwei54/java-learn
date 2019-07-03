package com.duwei.demo.java8.optional;

import java.util.Optional;

/**
 * @author duwei
 * @version 2018/6/4 22:35
 */
public class User {


    private String email;
    private String position;

    private String isocode;

    private Address address;

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    public User(String email, String isocode) {
        this.email = email;
        this.isocode = isocode;
    }

    public Optional<String> getPosition() {
        return Optional.ofNullable(position);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getIsocode() {
        return isocode;
    }

    public void setIsocode(String isocode) {
        this.isocode = isocode;
    }

}

class Address {
    private Country country;

    public Optional<Country> getCountry() {
        return Optional.ofNullable(country);
    }
}

class Country {
    private String isocode;

    public String getIsocode() {
        return isocode;
    }

    public void setIsocode(String isocode) {
        this.isocode = isocode;
    }
}