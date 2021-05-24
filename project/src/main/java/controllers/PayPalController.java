package controllers;

import services.IService;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PayPalController extends UnicastRemoteObject implements Serializable {
    IService service;

    public PayPalController() throws RemoteException {

    }

    public void setUp(IService service) {
        this.service = service;
    }

    public boolean handleCheckUser(String username, String password){
        return service.checkPayPalUser(username, password);
    }
}
