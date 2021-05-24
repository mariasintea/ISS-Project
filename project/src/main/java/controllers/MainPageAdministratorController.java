package controllers;

import model.domain.Product;
import services.IService;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class MainPageAdministratorController extends UnicastRemoteObject implements Serializable {
    Product selectedProduct;
    IService service;

    public MainPageAdministratorController() throws RemoteException {
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

    public void setSelectedProduct(Product product) {
        this.selectedProduct = product;
    }

    public List<Product> getProducts() {
        List<Product> productList = service.getAllProducts();
        return productList;
    }

    /**
     * deletes selected Product from database
     */
    public void handleDelete() {
        service.deleteProduct(selectedProduct.getId(), selectedProduct.getName(), selectedProduct.getPrice(), selectedProduct.getAvailableQuantity());
    }
}
