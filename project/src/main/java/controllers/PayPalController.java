package controllers;

import services.Service;

public class PayPalController {
    Service service;

    public PayPalController(){

    }

    public void setUp(Service service) {
        this.service = service;
    }

    public boolean handleCheckUser(String username, String password){
        return service.checkPayPalUser(username, password);
    }
}
