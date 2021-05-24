package model.domain;

import java.io.Serializable;

public class Address implements Serializable {
    int id;
    String street;
    int number;
    String city;
    String county;
    String country;

    public Address() {
    }

    public Address(int id, String street, int number, String city, String county, String country) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.city = city;
        this.county = county;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
