package controllers;

import model.domain.Product;
import services.Service;

import java.util.List;

public class MainPageSalesmanController{
    Service service;

    public MainPageSalesmanController() {
    }

    /**
     * makes the set up for controller
     * @param service - current service
     */
    public void setUp(Service service){
        this.service = service;
    }

    public Service getService(){
        return service;
    }

    public List<Product> getProducts() {
        List<Product> productList = service.getAllProducts();
        return productList;
    }
}