package model.domain;

import java.io.Serializable;

public class Order implements Serializable {
    int id;
    int address;
    String payment;

    public Order() {
    }

    public Order(int id, int address, String payment) {
        this.id = id;
        this.address = address;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
