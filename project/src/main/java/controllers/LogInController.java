package controllers;

import services.Service;

public class LogInController {
    Service service;

    public LogInController(){

    }

    public void setUp(Service service){
        this.service = service;
    }

    public String handleLogIn(String username, String password) {
        return service.checkUser(username, password);
    }
}
