package controllers;

import model.domain.Product;
import services.IService;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AddUpdateController  extends UnicastRemoteObject implements Serializable {
    IService service;
    String operation;
    Product selectedProduct;

    public AddUpdateController() throws RemoteException{
    }

    public void setUp(String operation, IService service){
        this.service = service;
        this.operation = operation;
    }

    public void setSelectedProduct(Product product) {
        this.selectedProduct = product;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void handleSendResponse(String name, String price, String quantity){
        if(operation.equals("update")) {
            service.updateProduct(selectedProduct.getId(), name, Double.valueOf(price), Integer.parseInt(quantity));
        }
        else{
            service.addProduct(name, Double.valueOf(price), Integer.parseInt(quantity));
        }
    }
}
