package controllers;

import model.domain.Product;
import services.Service;

import java.util.List;

public class MainPageAdministratorController{
    Product selectedProduct;
    Service service;

    public MainPageAdministratorController() {
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

    public void setSelectedProduct(Product product) {
        this.selectedProduct = product;
    }

    public List<Product> getProducts() {
        List<Product> productList = service.getAll();
        return productList;
    }

    /**
     * deletes selected Product from database
     */
    public void handleDelete() {
        service.delete(selectedProduct.getId(), selectedProduct.getName(), selectedProduct.getPrice(), selectedProduct.getAvailableQuantity());
    }
}
