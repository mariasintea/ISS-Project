package controllers;

import model.domain.Product;
import services.Service;

import java.util.List;

public class MainPageSalesmanController{
    Product selectedProduct;
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

    public void setSelectedProduct(Product product) {
        this.selectedProduct = product;
    }

    public Service getService(){
        return service;
    }

    public List<Product> getProducts() {
        List<Product> productList = service.getAll();
        return productList;
    }
}