package controllers;

import services.IService;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LogInController extends UnicastRemoteObject implements Serializable {
    IService service;

    public LogInController() throws RemoteException {

    }

    public void setUp(IService service){
        this.service = service;
    }

    public String handleLogIn(String username, String password) {
        return service.checkUser(username, password);
    }
}
