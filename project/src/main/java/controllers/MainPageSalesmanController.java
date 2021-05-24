package controllers;

import model.domain.Product;
import services.IService;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class MainPageSalesmanController  extends UnicastRemoteObject implements Serializable {
    IService service;

    public MainPageSalesmanController() throws RemoteException {
    }

    /**
     * makes the set up for controller
     * @param service - current service
     */
    public void setUp(IService service){
        this.service = service;
    }

    public IService getService(){
        return service;
    }

    public List<Product> getProducts() {
        List<Product> productList = service.getAllProducts();
        return productList;
    }
}